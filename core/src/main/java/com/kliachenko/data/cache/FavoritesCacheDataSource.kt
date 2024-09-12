package com.kliachenko.data.cache

import com.kliachenko.data.cache.dao.FavoriteDao
import com.kliachenko.data.cache.entity.FavoriteCache
import com.kliachenko.data.cache.entity.FilmDashboardCache

interface FavoritesCacheDataSource {

    interface Save {
        suspend fun save(favorite: FavoriteCache)
    }

    interface Read {
        suspend fun favorites(): List<FilmDashboardCache>

        suspend fun favoriteFilmsIds(): Set<Int>
    }

    interface Remove {
        suspend fun remove(filmId: Int)
    }

    interface Status {
        suspend fun isFavorite(filmId: Int): Boolean
    }

    interface MutableDetail: Save, Remove, Status

    interface MutableDashboard : MutableDetail, Read

    class Base(private val favoriteDao: FavoriteDao) : MutableDashboard, MutableDetail {

        override suspend fun save(favorite: FavoriteCache) {
            favoriteDao.addToFavorite(favorite)
        }

        override suspend fun favorites() = favoriteDao.favorites()

        override suspend fun remove(filmId: Int) {
            favoriteDao.removeFromFavorite(filmId)
        }

        override suspend fun favoriteFilmsIds(): Set<Int> =
            favoriteDao.favoriteFilmsIds().toSet()

        override suspend fun isFavorite(filmId: Int) = favoriteDao.isFavorite(filmId)

    }

}