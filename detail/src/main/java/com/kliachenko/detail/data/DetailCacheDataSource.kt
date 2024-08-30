package com.kliachenko.detail.data

import com.kliachenko.data.cache.dao.FavoriteDao
import com.kliachenko.data.cache.entity.FavoriteCache
import com.kliachenko.data.cache.entity.FilmDetailCache

interface DetailCacheDataSource {

    interface Save {
        suspend fun save(detailFilm: FilmDetailCache, filmId: FavoriteCache)
    }

    interface Read {
        suspend fun read(filmId: Int): FilmDetailCache
    }

    interface IsFavorite {
        suspend fun isFavorite(filmId: Int): Boolean
    }

    interface Mutable: Save, Read, IsFavorite

    class Base(private val favoriteDao: FavoriteDao):Mutable {

        override suspend fun save(detailFilm: FilmDetailCache, filmId: FavoriteCache) {
            favoriteDao.addToFavorite(filmId)
        }

        override suspend fun read(filmId: Int): FilmDetailCache {
            return favoriteDao.detailFavoriteFilm(filmId)
        }

        override suspend fun isFavorite(filmId: Int) = favoriteDao.isFavorite(filmId)

    }
}