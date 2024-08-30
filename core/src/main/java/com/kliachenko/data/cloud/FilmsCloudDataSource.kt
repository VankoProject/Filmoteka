package com.kliachenko.data.cloud

import com.kliachenko.core.BaseCloudDataSource

interface FilmsCloudDataSource {

    suspend fun loadFilms(category: String, page: Int): FilmsResponse

    suspend fun filmDetail(filmId: Int): FilmDetailCloud

    class Base(
        private val service: FilmsService,
    ) : BaseCloudDataSource(), FilmsCloudDataSource {

        override suspend fun loadFilms(category: String, page: Int): FilmsResponse =
            handleRequest(service.filmsByCategory(category = category, page = page))

        override suspend fun filmDetail(filmId: Int): FilmDetailCloud =
            handleRequest(service.filmDetail(filmId = filmId))
    }
}