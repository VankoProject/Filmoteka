package com.kliachenko.search.domain

import com.kliachenko.domain.FilmSearchDomain

interface SearchInteractor {

    suspend fun search(query: String): LoadResult

    suspend fun loadHistory(): List<FilmSearchDomain>

    suspend fun saveFilmToHistory(filmSearchDomain: FilmSearchDomain)

    class Base(
        private val repository: SearchRepository,
    ) : SearchInteractor {
        override suspend fun search(query: String): LoadResult {
            if (query.length < 2) return LoadResult.Empty
            return repository.search(query)
        }

        override suspend fun loadHistory(): List<FilmSearchDomain> {
            return repository.loadHistory()
        }

        override suspend fun saveFilmToHistory(filmSearchDomain: FilmSearchDomain) {
            repository.saveFilmToHistory(filmSearchDomain)
        }

    }
}