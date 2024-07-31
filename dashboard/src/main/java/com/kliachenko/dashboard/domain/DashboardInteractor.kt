package com.kliachenko.dashboard.domain

interface DashboardInteractor {

    suspend fun filmsByCategory(category: String): LoadResult

    suspend fun addToFavorite(filmId: Int)

    suspend fun removeFromFavorites(filmId: Int)

    fun needToLoadMoreFilms(): Boolean

    suspend fun loadMoreFilms(category: String): LoadResult

    class Base(
        private val repository: DashboardRepository,
    ) : DashboardInteractor {

        private val pageCounter: MutableMap<String, Int> = mutableMapOf()

        override suspend fun filmsByCategory(category: String): LoadResult {
            return repository.filmsByCategory(category = category, page = 1).also {
                pageCounter[category] = 1
            }
        }

        override suspend fun loadMoreFilms(category: String): LoadResult {
            //todo fix correct load and update data
            val currentPage = pageCounter[category] ?: 1
            val totalPages = repository.totalPages(category)

            if (currentPage >= totalPages) {
                return LoadResult.NoData("No more films")
            }

            val nextPage = currentPage + 1
            val result = repository.filmsByCategory(category, nextPage)
            if (result is LoadResult.Success) {
                pageCounter[category] = nextPage
            }
            return result
        }

        override suspend fun addToFavorite(filmId: Int) {
            repository.addToFavorite(filmId)
        }

        override suspend fun removeFromFavorites(filmId: Int) {
            repository.removeFromFavorites(filmId)
        }

        override fun needToLoadMoreFilms(): Boolean {
            return true
        }

    }
}