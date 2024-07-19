package com.kliachenko.data.cache

import com.kliachenko.data.cache.dao.FavoriteDao
import com.kliachenko.data.cache.entity.FilmCache

interface FavoritesCacheDataSource {

    interface Save {
        suspend fun save(filmId: Int)
    }

    interface Read {
        suspend fun favorites(): List<FilmCache>
    }

    interface Remove {
        suspend fun remove(filmId: Int)
    }

    interface Status {
        suspend fun isFavorite(filmId: Int): Boolean
    }

    interface Mutable : Save, Read, Remove, Status

    class Base(private val favoriteDao: FavoriteDao) : Mutable {

        override suspend fun save(filmId: Int) {
            favoriteDao.addToFavorite(filmId)
        }

        override suspend fun favorites() = favoriteDao.favorites()

        override suspend fun remove(filmId: Int) {
            favoriteDao.removeFromFavorite(filmId)
        }

        override suspend fun isFavorite(filmId: Int) = favoriteDao.isFavorite(filmId)

    }


}