package com.kliachenko.detail.domain

import com.kliachenko.domain.FilmDetailDomain
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailInteractorTest {

    private lateinit var repository: FakeDetailRepository
    private lateinit var interactor: DetailInteractor

    @Before
    fun setup() {
        repository = FakeDetailRepository()
        interactor = DetailInteractor.Base(repository)
    }

    @Test
    fun testSuccess() = runBlocking {
        val expectedFilmDetail = FilmDetailDomain(
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
            voteCount = 10
        )
        val expected = LoadResult.Success(expectedFilmDetail, isFavorite = false)
        repository.hasData(expected, expectedFilmDetail.filmId, isFavorite = false)
        val actual = interactor.filmDetail(0)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testError() = runBlocking {
        val expected = LoadResult.Error("Service unavailable")
        repository.hasError(expected)
        val actual = interactor.filmDetail(0)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun addToFavoriteThenRemove() = runBlocking {
        interactor.addToFavorite(1)
        repository.checkAddToFavorite(1)
        Assert.assertTrue(interactor.isFavorite(1))
        interactor.removeFromFavorite(1)
        repository.checkRemoveFromFavorite(1)
        Assert.assertFalse(interactor.isFavorite(1))
    }

    @Test
    fun testIsFavorite()= runBlocking {
        val expectedFilmDetail = FilmDetailDomain(
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
            voteCount = 10
        )
        val expected = LoadResult.Success(expectedFilmDetail, isFavorite = true)
        repository.hasData(expected, expectedFilmDetail.filmId, isFavorite = true)
        var actual = interactor.isFavorite(0)
        Assert.assertEquals(true, actual)
        interactor.removeFromFavorite(0)
        repository.checkRemoveFromFavorite(0)
        actual = interactor.isFavorite(0)
        Assert.assertEquals(false, actual)
    }

}

private class FakeDetailRepository : DetailRepository {

    private var actualLoadResult: LoadResult = LoadResult.Empty
    private var savedFavoriteFilmsId = mutableSetOf<Int>()

    override suspend fun filmDetail(filmId: Int): LoadResult {
        return actualLoadResult
    }

    override suspend fun addToFavorite(filmId: Int) {
        savedFavoriteFilmsId.add(filmId)
        if(actualLoadResult is LoadResult.Success) {
            actualLoadResult = (actualLoadResult as LoadResult.Success).copy(isFavorite = true)
        }
    }

    override suspend fun removeFromFavorite(filmId: Int) {
        savedFavoriteFilmsId.remove(filmId)
        if(actualLoadResult is LoadResult.Success) {
            actualLoadResult = (actualLoadResult as LoadResult.Success).copy(isFavorite = false)
        }
    }

    override suspend fun isFavorite(filmId: Int): Boolean {
        return savedFavoriteFilmsId.contains(filmId)
    }

    fun hasData(success: LoadResult.Success, filmId: Int, isFavorite: Boolean = false) {
        actualLoadResult = success
        if(isFavorite) {
            savedFavoriteFilmsId.add(filmId)
        }
    }

    fun hasError(error: LoadResult.Error) {
        actualLoadResult = error
    }

    fun checkAddToFavorite(expectedId: Int) {
        Assert.assertTrue(savedFavoriteFilmsId.contains(expectedId))
    }

    fun checkRemoveFromFavorite(expectedId: Int) {
        Assert.assertFalse(savedFavoriteFilmsId.contains(expectedId))
    }

}