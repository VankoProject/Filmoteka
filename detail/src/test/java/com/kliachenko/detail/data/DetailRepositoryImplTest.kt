package com.kliachenko.detail.data

import com.kliachenko.core.HandleError
import com.kliachenko.data.cache.FavoritesCacheDataSource
import com.kliachenko.data.cache.entity.FavoriteCache
import com.kliachenko.data.cloud.Countries
import com.kliachenko.data.cloud.FilmDetailCloud
import com.kliachenko.data.cloud.FilmsCloudDataSource
import com.kliachenko.data.cloud.Genre
import com.kliachenko.data.mapper.FilmDetailMapper
import com.kliachenko.detail.domain.LoadResult
import com.kliachenko.domain.FilmDetailDomain
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class DetailRepositoryImplTest {

    private lateinit var cloudDataSource: FakeCloudDataSource
    private lateinit var favoriteCacheDataSource: FakeFavoriteCacheDataSource
    private lateinit var handleError: FakeHandleError
    private lateinit var mapToDomain: FakeDetailMapper
    private lateinit var repository: DetailRepositoryImpl

    @Before
    fun setup() {
        cloudDataSource = FakeCloudDataSource()
        favoriteCacheDataSource = FakeFavoriteCacheDataSource()
        handleError = FakeHandleError()
        mapToDomain = FakeDetailMapper()
        repository = DetailRepositoryImpl(
            cloudDataSource = cloudDataSource,
            favoritesCacheDataSource = favoriteCacheDataSource,
            mapToDomain = mapToDomain,
            handleError = handleError
        )
    }

    @Test
    fun testFirstRunWithErrorNoInternetConnection() = runBlocking {
        cloudDataSource.errorLoadResult(UnknownHostException())
        val actual: LoadResult = repository.filmDetail(filmId = 0)
        val expected: LoadResult = LoadResult.Error("No internet connection")
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testFirstRunWithErrorServiceUnavailable() = runBlocking {
        cloudDataSource.errorLoadResult(Exception("Service unavailable"))
        val actual: LoadResult = repository.filmDetail(filmId = 0)
        val expected: LoadResult = LoadResult.Error("Service unavailable")
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testFirstRunWithSuccess() = runBlocking {
        cloudDataSource.successLoadResult()
        val actual: LoadResult = repository.filmDetail(filmId = 0)
        val expected: LoadResult = LoadResult.Success(
            FilmDetailDomain(
                filmId = 0,
                adult = false,
                genres = listOf("Action"),
                homePage = "https://www.amazon.com/",
                productionCountries = listOf("USA"),
                originalLanguage = "en",
                title = "Film#0",
                overview = "Overview#0",
                posterPath = "path#0",
                releaseDate = "2020",
                runtime = 100,
                tagline = "Tagline#0",
                voteAverage = 5.0,
                voteCount = 10,
            ), isFavorite = false
        )
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testAddToFavoriteThenRemove() = runBlocking {
        repository.addToFavorite(0)
        favoriteCacheDataSource.checkAddedToFavorite(0)
        repository.addToFavorite(1)
        favoriteCacheDataSource.checkAddedToFavorite(1)
        repository.removeFromFavorite(1)
        favoriteCacheDataSource.checkRemovedFromFavorite(1)
    }

    @Test
    fun testIsFavorite() = runBlocking {
        repository.isFavorite(0)
        favoriteCacheDataSource.checkIsFavorite(false)
        repository.addToFavorite(0)
        favoriteCacheDataSource.checkIsFavorite(true)
        repository.removeFromFavorite(0)
        favoriteCacheDataSource.checkIsFavorite(false)

    }
}

private class FakeCloudDataSource : FilmsCloudDataSource.FilmDetail {

    private var isSuccessResult: Boolean = false
    private lateinit var exception: Exception

    override suspend fun filmDetail(filmId: Int): FilmDetailCloud {
        if (isSuccessResult) {
            return FilmDetailCloud(
                filmId = 0,
                adult = false,
                genres = listOf(Genre(0, "Action")),
                homepage = "https://www.amazon.com/",
                productionCountries = listOf(Countries("USA", "USA")),
                originalLanguage = "en",
                title = "Film#0",
                overview = "Overview#0",
                posterPath = "path#0",
                releaseDate = "2020",
                runtime = 100,
                tagline = "Tagline#0",
                voteAverage = 5.0,
                voteCount = 10,
            )
        } else throw exception
    }

    fun successLoadResult() {
        isSuccessResult = true
    }

    fun errorLoadResult(exception: Exception) {
        isSuccessResult = false
        this.exception = exception
    }

}

private class FakeFavoriteCacheDataSource : FavoritesCacheDataSource.MutableDetail {

    private var actualFavoriteId: Boolean = false
    private var actualFilmsId: MutableSet<Int> = mutableSetOf()

    override suspend fun save(favorite: FavoriteCache) {
        actualFavoriteId = true
        actualFilmsId.add(favorite.filmId)
    }

    override suspend fun remove(filmId: Int) {
        actualFavoriteId = false
        actualFilmsId.remove(filmId)
    }

    override suspend fun isFavorite(filmId: Int): Boolean {
        return actualFilmsId.contains(filmId)
    }

    fun checkAddedToFavorite(expectedFilmId: Int) {
        Assert.assertTrue(actualFilmsId.contains(expectedFilmId))
    }

    fun checkRemovedFromFavorite(expectedFilmId: Int) {
        Assert.assertFalse(actualFilmsId.contains(expectedFilmId))
    }

    fun checkIsFavorite(expect: Boolean) {
        Assert.assertEquals(expect, actualFavoriteId)
    }

}

private class FakeHandleError : HandleError<String> {

    override fun handle(e: Exception): String {
        return if (e is UnknownHostException) "No internet connection"
        else "Service unavailable"
    }

}

private class FakeDetailMapper : FilmDetailMapper.ToDomain {
    override fun map(
        filmId: Int,
        adult: Boolean,
        genres: List<String>,
        homePage: String,
        productionCountries: List<String>,
        originalLanguage: String,
        title: String,
        overview: String,
        posterPath: String,
        releaseDate: String,
        runtime: Int,
        tagline: String,
        voteAverage: Double,
        voteCount: Int,
    ): FilmDetailDomain {
        return FilmDetailDomain(
            filmId,
            adult,
            genres,
            homePage,
            productionCountries,
            originalLanguage,
            title,
            overview,
            posterPath,
            releaseDate,
            runtime,
            tagline,
            voteAverage,
            voteCount
        )
    }
}
