package com.kliachenko.data.mapper

import com.kliachenko.data.cache.entity.FilmDetailCache
import com.kliachenko.domain.FilmDetailDomain

interface FilmDetailMapper<T : Any> {

    fun map(
        filmId: Int,
        adult: Boolean,
        genres: List<String>,
        homePage: String,
        productionCountries: List<String>,
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

    interface ToCache : FilmDetailMapper<FilmDetailCache> {

        object Base : ToCache {
            override fun map(
                filmId: Int,
                adult: Boolean,
                genres: List<String>,
                homePage: String,
                productionCountries: List<String>,
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
                productionCountries = productionCountries,
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

    interface ToDomain : FilmDetailMapper<FilmDetailDomain> {

        object Base : ToDomain {
            override fun map(
                filmId: Int,
                adult: Boolean,
                genres: List<String>,
                homePage: String,
                productionCountries: List<String>,
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
                productionCountries = productionCountries,
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

    interface FilmDetailId : FilmDetailMapper<Int> {

        object Base : FilmDetailId {
            override fun map(
                filmId: Int,
                adult: Boolean,
                genres: List<String>,
                homePage: String,
                productionCountries: List<String>,
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

interface MapFilmDetail {

    fun <T : Any> map(mapper: FilmDetailMapper<T>): T
}