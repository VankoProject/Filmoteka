package com.kliachenko.detail.domain

interface DetailInteractor {

    suspend fun filmDetail(filmId: Int): LoadResult

    suspend fun addToFavorite(filmId: Int)

    suspend fun removeFromFavorite(filmId: Int)

    suspend fun isFavorite(filmId: Int): Boolean


    class Base(
        private val repository: DetailRepository,
    ) : DetailInteractor {
        override suspend fun filmDetail(filmId: Int): LoadResult {
            return repository.filmDetail(filmId)
        }

        override suspend fun addToFavorite(filmId: Int) {
            repository.addToFavorite(filmId)
        }

        override suspend fun removeFromFavorite(filmId: Int) {
            repository.removeFromFavorite(filmId)
        }

        override suspend fun isFavorite(filmId: Int): Boolean {
            return repository.isFavorite(filmId)
        }

    }
}