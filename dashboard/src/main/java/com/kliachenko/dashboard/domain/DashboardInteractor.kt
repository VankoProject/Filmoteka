package com.kliachenko.dashboard.domain

interface DashboardInteractor {

    suspend fun filmsByCategory(category: String, page: Int): LoadResult

    suspend fun addToFavorite(filmId: Int)

    suspend fun removeFromFavorites(filmId: Int)

    class Base(
        private val repository: DashboardRepository,
    ) : DashboardInteractor {

        override suspend fun filmsByCategory(category: String, page: Int): LoadResult {
            return repository.filmsByCategory(category, page)
        }

        override suspend fun addToFavorite(filmId: Int) {
            repository.addToFavorite(filmId)
        }

        override suspend fun removeFromFavorites(filmId: Int) {
            repository.removeFromFavorites(filmId)
        }

    }
}