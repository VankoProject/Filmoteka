package com.kliachenko.search.domain

import com.kliachenko.domain.FilmSearchDomain

interface SearchRepository {

    suspend fun search(query: String): LoadResult

    suspend fun loadHistory(): List<FilmSearchDomain>

    suspend fun saveFilmToHistory(filmSearchDomain: FilmSearchDomain)

}

interface LoadResult {

    fun <T: Any> map(mapper: Mapper<T>): T

    interface Mapper<T: Any> {

        fun mapSuccess(items: List<FilmSearchDomain>): T

        fun mapError(message: String): T

        fun mapEmpty(message: String): T
    }

    data class Empty(private val message: String): LoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapEmpty(message)
        }
    }

    data class Error(private val message: String): LoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapError(message)
        }
    }

    data class Success(private val items: List<FilmSearchDomain>): LoadResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapSuccess(items)
        }
    }
}