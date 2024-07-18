package com.kliachenko.data.mapper

import com.kliachenko.data.cache.FavoriteCache
import com.kliachenko.data.cache.FilmCache
import com.kliachenko.domain.FilmDomain

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

        object Base : ToCache {
            override fun map(
                id: Int,
                overview: String,
                posterPath: String,
                releaseDate: String,
                title: String,
                voteAverage: Double,
            ) = FilmCache(
                id = id,
                overview = overview,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage,
            )
        }
    }

    interface ToDomain: FilmsMapper<FilmDomain> {

        object Base: ToDomain {
            override fun map(
                id: Int,
                overview: String,
                posterPath: String,
                releaseDate: String,
                title: String,
                voteAverage: Double,
            ) = FilmDomain (
                id = id,
                overview = overview,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage,
            )
        }
    }

    interface ToFavorite: FilmsMapper<FavoriteCache> {

        object Base: ToFavorite {
            override fun map(
                id: Int,
                overview: String,
                posterPath: String,
                releaseDate: String,
                title: String,
                voteAverage: Double,
            ) : FavoriteCache = FavoriteCache(
                filmId = id
            )

        }
    }

}

interface MapFilms {

    fun <T: Any> map(mapper: FilmsMapper<T>): T
}