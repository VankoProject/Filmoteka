package com.kliachenko.detail.data

import com.kliachenko.data.cache.dao.FavoriteDao
import com.kliachenko.data.cache.entity.FavoriteCache

interface DetailCacheDataSource {

    interface Save {
        suspend fun save(detailFilm: FilmDetaiCache, filmId: FavoriteCache)
    }

    interface Read {
        suspend fun read(filmId: Int): FilmDetailCache
    }

    interface IsFavorite {
        suspend fun isFavorite(filmId: Int): Boolean
    }

    interface Mutable: Save, Read, IsFavorite

    class Base(private val favoriteDao: FavoriteDao):Mutable {

        override suspend fun save(detailFilm: FilmDetaiCache, filmId: FavoriteCache) {
            favoriteDao.addToFavorite(filmId)
        }

        override suspend fun read(filmId: Int): FilmDetailCache {
            return
        }

        override suspend fun isFavorite(filmId: Int) = favoriteDao.isFavorite(filmId)

    }
}