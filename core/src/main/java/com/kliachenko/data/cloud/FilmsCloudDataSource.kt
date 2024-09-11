package com.kliachenko.data.cloud

import com.kliachenko.core.BaseCloudDataSource

interface FilmsCloudDataSource {

    interface Films {
        suspend fun loadFilms(category: String, page: Int): FilmsResponse
    }

    interface FilmDetail {
        suspend fun filmDetail(filmId: Int): FilmDetailCloud
    }

    interface Mutable: Films, FilmDetail

    class Base(
        private val service: FilmsService,
    ) : BaseCloudDataSource(), Mutable {

        override suspend fun loadFilms(category: String, page: Int): FilmsResponse =
            handleRequest(service.filmsByCategory(category = category, page = page))

        override suspend fun filmDetail(filmId: Int): FilmDetailCloud =
            handleRequest(service.filmDetail(filmId = filmId))
    }
}