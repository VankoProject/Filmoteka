package com.kliachenko.dashboard.domain

import com.kliachenko.domain.FilmDomain

interface DashboardInteractor {

    suspend fun loadDataByPage(category: String): LoadResult

    suspend fun loadDataByCategory(category: String): List<FilmDomain>

    suspend fun loadMoreFilms(category: String): LoadResult

    suspend fun loadPreviousFilms(category: String): LoadResult

    suspend fun addToFavorite(filmId: Int)

    suspend fun removeFromFavorites(filmId: Int)

    suspend fun needToLoadMoreData(lastVisibleItemPosition: Int, category: String): Boolean

    class Base(
        private val repository: DashboardRepository,
    ) : DashboardInteractor {

        private val pageCounter: MutableMap<String, Int> = mutableMapOf()

        override suspend fun loadDataByPage(category: String): LoadResult {
            val currentPage = pageCounter[category] ?: 1
            return repository.filmsByCategoryAndPages(category, currentPage).also {
                pageCounter[category] = currentPage
            }
        }

        override suspend fun loadDataByCategory(category: String): List<FilmDomain> {
            return repository.allCachedFilmsByCategory(category)
        }

        override suspend fun loadMoreFilms(category: String): LoadResult {
            val currentPage = pageCounter[category] ?: 1
            val totalPages = repository.totalPages(category)
            if (currentPage > totalPages) {
                return LoadResult.NoData("No more films")
            }
            val nextPage = currentPage + 1
            val result = repository.filmsByCategoryAndPages(category, nextPage)
            if (result is LoadResult.Success) {
                pageCounter[category] = nextPage
            }
            return result
        }

        override suspend fun loadPreviousFilms(category: String): LoadResult {
            val currentPage = pageCounter[category] ?: 1
            val previousPage = currentPage - 1
            val pageToLoad = if (previousPage < 1) 1 else previousPage
            val result = repository.filmsByCategoryAndPages(category, pageToLoad)
            if (result is LoadResult.Success) {
                pageCounter[category] = pageToLoad
            }
            return result
        }

        override suspend fun addToFavorite(filmId: Int) {
            repository.addToFavorite(filmId)
        }

        override suspend fun removeFromFavorites(filmId: Int) {
            repository.removeFromFavorites(filmId)
        }

        override suspend fun needToLoadMoreData(
            lastVisibleItemPosition: Int,
            category: String,
        ): Boolean =
            with(repository.allCachedFilmsByCategory(category)) {
                return@with isNotEmpty() && size - 1 == lastVisibleItemPosition
            }

    }
}