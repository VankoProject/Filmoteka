package com.kliachenko.data.cloud

import com.kliachenko.core.BaseCloudDataSource

interface FilmsCloudDataSource {

    interface Films {
        suspend fun loadFilms(category: String, page: Int): FilmsResponse
    }

    interface FilmDetail {
        suspend fun filmDetail(filmId: Int): FilmDetailCloud
    }

    interface FilmsSearch {
        suspend fun filmsSearch(query: String): FilmsSearchResponse
    }

    interface Mutable : Films, FilmDetail, FilmsSearch

    class Base(
        private val service: FilmsService,
    ) : BaseCloudDataSource(), Mutable {

        override suspend fun loadFilms(category: String, page: Int): FilmsResponse =
            handleRequest(service.filmsByCategory(category = category, page = page))

        override suspend fun filmDetail(filmId: Int): FilmDetailCloud =
            handleRequest(service.filmDetail(filmId = filmId))

        override suspend fun filmsSearch(query: String): FilmsSearchResponse =
            handleRequest(service.searchFilm(query))

    }
}