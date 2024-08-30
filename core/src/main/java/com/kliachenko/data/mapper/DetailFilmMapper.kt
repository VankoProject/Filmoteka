package com.kliachenko.data.mapper

import com.kliachenko.data.cache.entity.FilmDetailCache

interface DetailFilmMapper<T : Any> {

    fun map(
        filmId: Int,
        adult: Boolean,
        genres: List<String>,
        homePage: String,
        originalLanguage: String,
        title: String,
        overview: String,
        posterPath: String,
        releaseDate: String,
        runtime: Int,
        tagline: String,
        voteAverage: Double,
        voteCount: Int,
    ): T

    interface ToCache : DetailFilmMapper<FilmDetailCache> {

        object Base : ToCache {
            override fun map(
                filmId: Int,
                adult: Boolean,
                genres: List<String>,
                homePage: String,
                originalLanguage: String,
                title: String,
                overview: String,
                posterPath: String,
                releaseDate: String,
                runtime: Int,
                tagline: String,
                voteAverage: Double,
                voteCount: Int,
            ) = FilmDetailCache(
                filmId = filmId,
                adult = adult,
                genres = genres,
                homePage = homePage,
                originalLanguage = originalLanguage,
                title = title,
                overview = overview,
                posterPath = posterPath,
                releaseDate = releaseDate,
                runtime = runtime,
                tagline = tagline,
                voteAverage = voteAverage,
                voteCount = voteCount
            )
        }
    }

    interface ToDomain : DetailFilmMapper<FilmDetailDomain> {

        object Base : ToDomain {
            override fun map(
                filmId: Int,
                adult: Boolean,
                genres: List<String>,
                homePage: String,
                originalLanguage: String,
                title: String,
                overview: String,
                posterPath: String,
                releaseDate: String,
                runtime: Int,
                tagline: String,
                voteAverage: Double,
                voteCount: Int,
            ) = FilmDetailDomain(
                filmId = filmId,
                adult = adult,
                genres = genres,
                homePage = homePage,
                originalLanguage = originalLanguage,
                title = title,
                overview = overview,
                posterPath = posterPath,
                releaseDate = releaseDate,
                runtime = runtime,
                tagline = tagline,
                voteAverage = voteAverage,
                voteCount = voteCount
            )
        }
    }

    interface ToFavorite : DetailFilmMapper<FilmDetailDomain> {

        object Base : ToFavorite {
            override fun map(
                filmId: Int,
                adult: Boolean,
                genres: List<String>,
                homePage: String,
                originalLanguage: String,
                title: String,
                overview: String,
                posterPath: String,
                releaseDate: String,
                runtime: Int,
                tagline: String,
                voteAverage: Double,
                voteCount: Int,
            ) = filmId
        }
    }
}

interface MapDetailFilm {

    fun <T : Any> map(mapper: DetailFilmMapper<T>): T
}