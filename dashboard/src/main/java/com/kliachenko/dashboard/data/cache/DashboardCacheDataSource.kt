package com.kliachenko.dashboard.data.cache

import com.kliachenko.data.cache.dao.FilmsDao
import com.kliachenko.data.cache.entity.FilmCache
import com.kliachenko.data.cache.entity.FilmsAndCategoryRelationCache

interface DashboardCacheDataSource {

    interface SaveFilm {
        suspend fun save(film: FilmCache)
    }

    interface ReadFilm {
        suspend fun read(filmId: Int): FilmCache
    }

    interface SaveRelation {
        suspend fun saveRelation(relation: FilmsAndCategoryRelationCache)
    }

    interface ReadFilms {
        suspend fun filmsByCategoryAndPage(categoryName: String, page: Int): List<FilmCache>

        suspend fun filmsByCategory(categoryName: String): List<FilmCache>
    }

    interface Mutable : SaveFilm, ReadFilms, ReadFilm, SaveRelation

    class Base(private val filmsDao: FilmsDao) : Mutable {

        override suspend fun save(film: FilmCache) {
            filmsDao.saveFilm(film)
        }

        override suspend fun read(filmId: Int) = filmsDao.film(filmId)


        override suspend fun saveRelation(relation: FilmsAndCategoryRelationCache) {
            filmsDao.saveRelation(relation)
        }

        override suspend fun filmsByCategoryAndPage(categoryName: String, page: Int) =
            filmsDao.byCategoryAndPages(categoryName, page)

        override suspend fun filmsByCategory(categoryName: String) =
            filmsDao.byCategory(categoryName)

    }
}