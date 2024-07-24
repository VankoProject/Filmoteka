package com.kliachenko.dashboard.domain

import com.kliachenko.domain.FilmDomain

interface DashboardRepository {

    suspend fun filmsByCategory(category: String): DashboardResult

    suspend fun addToFavorite(filmId: Int)

    suspend fun removeFromFavorites(filmId: Int)

}

interface DashboardResult {

    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {

        fun mapSuccess(items: List<FilmDomain>, favoriteFilmIds: Set<Int>): T

        fun mapError(message: String): T

        fun mapEmpty(): T
    }

    object Empty : DashboardResult {
        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.mapEmpty()
    }

    data class Success(private val items: List<FilmDomain>, private val favoriteFilmIds: Set<Int>) :
        DashboardResult {
        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.mapSuccess(items = items, favoriteFilmIds = favoriteFilmIds)
    }

    data class Error(private val message: String) : DashboardResult {
        override fun <T : Any> map(mapper: Mapper<T>) = mapper.mapError(message)
    }

}