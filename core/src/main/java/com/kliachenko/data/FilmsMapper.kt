package com.kliachenko.data

import com.kliachenko.data.cache.FilmCache

interface FilmsMapper<T : Any> {

    fun map(
        id: Int,
        overview: String,
        posterPath: String,
        releaseDate: String,
        title: String,
        voteAverage: Double,
    ): T

    interface ToCache : FilmsMapper<FilmCache> {

        class Base(private val query: String) : ToCache {
            override fun map(
                id: Int,
                overview: String,
                posterPath: String,
                releaseDate: String,
                title: String,
                voteAverage: Double,
            ) = FilmCache(
                query = query,
                id = id,
                overview = overview,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage,
                isFavorite = false
            )
        }
    }
}

interface MapResponse {

    fun <T: Any> map(mapper: FilmsMapper<T>): T
}