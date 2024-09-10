package com.kliachenko.detail.data

import com.kliachenko.core.HandleError
import com.kliachenko.data.cache.FavoritesCacheDataSource
import com.kliachenko.data.cache.entity.FavoriteCache
import com.kliachenko.data.cloud.FilmsCloudDataSource
import com.kliachenko.data.mapper.FilmDetailMapper
import com.kliachenko.detail.domain.DetailRepository
import com.kliachenko.detail.domain.LoadResult

class DetailRepositoryImpl(
    private val cloudDataSource: FilmsCloudDataSource.FilmDetail,
    private val favoritesCacheDataSource: FavoritesCacheDataSource.MutableDetail,
    private val mapToDomain: FilmDetailMapper.ToDomain = FilmDetailMapper.ToDomain.Base,
    private val handleError: HandleError<String>,
) : DetailRepository {

    override suspend fun filmDetail(filmId: Int): LoadResult {
        val isFavorite = favoritesCacheDataSource.isFavorite(filmId)
        return try {
            val response = cloudDataSource.filmDetail(filmId)
            val detailFilm = response.map(mapToDomain)
            LoadResult.Success(detailFilm, isFavorite)
        } catch (e: Exception) {
            LoadResult.Error(handleError.handle(e))
        }
    }

    override suspend fun addToFavorite(filmId: Int) {
        favoritesCacheDataSource.save(FavoriteCache(filmId))

    }

    override suspend fun removeFromFavorite(filmId: Int) {
        favoritesCacheDataSource.remove(filmId)
    }

    override suspend fun isFavorite(filmId: Int): Boolean {
        return favoritesCacheDataSource.isFavorite(filmId)
    }
}