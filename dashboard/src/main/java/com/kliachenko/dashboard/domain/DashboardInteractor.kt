package com.kliachenko.dashboard.domain

interface DashboardInteractor {

    suspend fun filmsByCategory(category: String): LoadResult

    suspend fun addToFavorite(filmId: Int)

    suspend fun removeFromFavorites(filmId: Int)

    fun needToLoadMoreFilms(lastVisibleItemPosition: Int): Boolean

    suspend fun loadMoreFilms(category: String): LoadResult

    class Base(
        private val repository: DashboardRepository,
    ) : DashboardInteractor {

        private var currentPage = 1
        private var totalPages = 1
        private var isLoading = false

        override suspend fun filmsByCategory(category: String): LoadResult {
            TODO("Not yet implemented")
        }

        override suspend fun addToFavorite(filmId: Int) {
            repository.addToFavorite(filmId)
        }

        override suspend fun removeFromFavorites(filmId: Int) {
            repository.removeFromFavorites(filmId)
        }

        override fun needToLoadMoreFilms(lastVisibleItemPosition: Int): Boolean {
            TODO("Not yet implemented")
        }

        override suspend fun loadMoreFilms(category: String): LoadResult {
            TODO("Not yet implemented")
        }


    }
}