package com.kliachenko.search.data.cache

import com.kliachenko.data.cache.dao.SearchHistoryDao
import com.kliachenko.data.cache.entity.FilmSearchHistoryCache

interface SearchHistoryDataSource {

    interface Save {
        suspend fun save(searchFilm: FilmSearchHistoryCache)
    }

    interface Read {
        suspend fun read(): List<FilmSearchHistoryCache>
    }

    interface Mutable : Save, Read

    data class Base(private val searchHistoryDao: SearchHistoryDao) : Mutable {

        override suspend fun save(searchFilm: FilmSearchHistoryCache) {
            searchHistoryDao.saveFilmToHistory(searchFilm)
        }

        override suspend fun read(): List<FilmSearchHistoryCache> =
            searchHistoryDao.allHistoryFilms()

    }
}