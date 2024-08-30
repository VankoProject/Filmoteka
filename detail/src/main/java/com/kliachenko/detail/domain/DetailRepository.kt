package com.kliachenko.detail.domain

import com.kliachenko.data.cache.entity.FilmDetailCache
import com.kliachenko.domain.FilmDetailDomain

interface DetailRepository {

    suspend fun filmDetail(filmId: Int): LoadResult

    suspend fun addToFavorite(film: FilmDetailCache, filmId: Int)

    suspend fun removeFromFavorite(filmId: Int)

}

interface LoadResult {

    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {

        fun mapSuccess(item: FilmDetailDomain): T

        fun mapError(message: String): T

        fun mapEmpty(): T
    }

    object Empty : LoadResult {
        override fun <T : Any> map(mapper: Mapper<T>) = mapper.mapEmpty()
    }

    data class Success(
        private val item: FilmDetailDomain,
    ) : LoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.mapSuccess(item)
    }

    data class Error(
        private val message: String,
    ) : LoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T =
            mapper.mapError(message)
    }

}