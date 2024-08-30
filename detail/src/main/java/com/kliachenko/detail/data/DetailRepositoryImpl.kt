package com.kliachenko.detail.data

import com.kliachenko.core.HandleError
import com.kliachenko.data.cache.FavoritesCacheDataSource
import com.kliachenko.data.cache.entity.FavoriteCache
import com.kliachenko.data.cache.entity.FilmDetailCache
import com.kliachenko.data.cloud.FilmsCloudDataSource
import com.kliachenko.data.mapper.DetailFilmMapper
import com.kliachenko.detail.domain.DetailRepository
import com.kliachenko.detail.domain.LoadResult

class DetailRepositoryImpl(
    private val cloudDataSource: FilmsCloudDataSource,
    private val detailCacheDataSource: DetailCacheDataSource.Mutable,
    private val favoritesCacheDataSource: FavoritesCacheDataSource.Mutable,
    private val mapToDomain: DetailFilmMapper.ToDomain = DetailFilmMapper.ToDomain.Base,
    private val handleError: HandleError<String>,
) : DetailRepository {

    override suspend fun filmDetail(filmId: Int): LoadResult {
        return try {
            val isFavorite = favoritesCacheDataSource.isFavorite(filmId)
            if (isFavorite) {
                val cache = detailCacheDataSource.read(filmId)
                LoadResult.Success(cache.map(mapToDomain))
            } else {
                val response = cloudDataSource.filmDetail(filmId)
                val detailFilm = response.map(mapToDomain)
                LoadResult.Success(detailFilm)
            }
        } catch (e: Exception) {
            LoadResult.Error(handleError.handle(e))
        }

    }

    override suspend fun addToFavorite(film: FilmDetailCache) {
        favoritesCacheDataSource.save(FavoriteCache(film.filmId))
        detailCacheDataSource.save(film)
    }

    override suspend fun removeFromFavorite(filmId: Int) {
        favoritesCacheDataSource.remove(filmId)
    }
}