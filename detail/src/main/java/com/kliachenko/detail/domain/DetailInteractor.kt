package com.kliachenko.detail.domain

import com.kliachenko.data.cache.entity.FilmDetailCache

interface DetailInteractor {

    suspend fun filmDetail(filmId: Int): LoadResult

    suspend fun addToFavorite(film: FilmDetailCache)

    suspend fun removeFromFavorite(filmId: Int)


    class Base(private val repository: DetailRepository) : DetailInteractor {
        override suspend fun filmDetail(filmId: Int): LoadResult {
            return repository.filmDetail(filmId)
        }

        override suspend fun addToFavorite(film: FilmDetailCache) {
            repository.addToFavorite(film)
        }

        override suspend fun removeFromFavorite(filmId: Int) {
            repository.removeFromFavorite(filmId)
        }

    }
}