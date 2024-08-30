package com.kliachenko.detail.data

import com.kliachenko.data.cache.dao.FilmsDao
import com.kliachenko.data.cache.entity.FilmDetailCache

interface DetailCacheDataSource {

    interface Save {
        suspend fun save(detailFilm: FilmDetailCache)
    }

    interface Read {
        suspend fun read(filmId: Int): FilmDetailCache
    }

    interface Mutable : Save, Read

    class Base(private val filmsDao: FilmsDao) : Mutable {

        override suspend fun save(detailFilm: FilmDetailCache) {
            filmsDao.saveDetailFilm(detailFilm)
        }

        override suspend fun read(filmId: Int): FilmDetailCache =
            filmsDao.detailFavoriteFilm(filmId)
    }
}