package com.kliachenko.detail.domain

import com.kliachenko.domain.FilmDetailDomain

interface DetailRepository {

    suspend fun filmDetail(filmId: Int): LoadResult

    suspend fun addToFavorite(filmId: Int)

    suspend fun removeFromFavorite(filmId: Int)

    suspend fun isFavorite(filmId: Int): Boolean

}

interface LoadResult {

    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {

        fun mapSuccess(item: FilmDetailDomain, isFavorite: Boolean): T

        fun mapError(message: String): T

        fun mapEmpty(): T
    }

    object Empty : LoadResult {
        override fun <T : Any> map(mapper: Mapper<T>) = mapper.mapEmpty()
    }

    data class Success(
        private val item: FilmDetailDomain,
        private val isFavorite: Boolean
    ) : LoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.mapSuccess(item, isFavorite)
    }

    data class Error(
        private val message: String,
    ) : LoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.mapError(message)
    }

}