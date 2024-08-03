package com.kliachenko.dashboard.domain

import com.kliachenko.domain.FilmDomain

interface DashboardRepository {

    suspend fun filmsByCategoryAndPages(category: String, page: Int): LoadResult

    suspend fun allFilmsByCategory(category: String): List<FilmDomain>

    suspend fun addToFavorite(filmId: Int)

    suspend fun removeFromFavorites(filmId: Int)

    suspend fun totalPages(category: String): Int

}

interface LoadResult {

    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {

        fun mapSuccess(items: List<FilmDomain>, favoriteFilmIds: Set<Int>): T

        fun mapError(message: String): T

        fun mapEmpty(): T

        fun mapNoData(message: String): T
    }

    object Empty : LoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.mapEmpty()
    }

    data class Success(
        private val items: List<FilmDomain>,
        private val favoriteFilmIds: Set<Int>,
    ) :
        LoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.mapSuccess(items = items, favoriteFilmIds = favoriteFilmIds)
    }

    data class Error(private val message: String) : LoadResult {
        override fun <T : Any> map(mapper: Mapper<T>) = mapper.mapError(message)
    }

    data class NoData(private val message: String) : LoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.mapNoData(message)
    }

}