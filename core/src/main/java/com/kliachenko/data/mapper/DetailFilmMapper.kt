package com.kliachenko.data.mapper

interface DetailFilmMapper<T : Any> {

    fun map(
        filmId: Int,
        adult: Boolean,
        genres: List<String>,
        homePage: String,
        originalLanguage: String,
        title: String,
        overView: String,
        posterPath: String,
        releaseDate: String,
        runtime: Int,
        tagLine: String,
        voteAverage: Int,
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
                overView: String,
                posterPath: String,
                releaseDate: String,
                runtime: Int,
                tagLine: String,
                voteAverage: Int,
                voteCount: Int,
            ) = FilmDetailCache(
                id = filmId,
                adult = adult,
                genres = genres,
                homePage = homePage,
                originalLanguage = originalLanguage,
                title = title,
                overView = overView,
                posterPath = posterPath,
                releaseDate = releaseDate,
                runtime = runtime,
                tagLine = tagLine,
                voteAverage = voteAverage,
                voteCount = voteCount
            )
        }
    }

    interface ToDomain : DetailFilmMapper<FilmDetailDomain>

    interface ToFavorite : DetailFilmMapper<FilmDetailDomain> {
        object Base : ToFavorite {
            override fun map(
                filmId: Int,
                adult: Boolean,
                genres: List<String>,
                homePage: String,
                originalLanguage: String,
                title: String,
                overView: String,
                posterPath: String,
                releaseDate: String,
                runtime: Int,
                tagLine: String,
                voteAverage: Int,
                voteCount: Int,
            ) = FilmDetailDomain(
                id = filmId,
                adult = adult,
                genres = genres,
                homePage = homePage,
                originalLanguage = originalLanguage,
                title = title,
                overView = overView,
                posterPath = posterPath,
                releaseDate = releaseDate,
                runtime = runtime,
                tagLine = tagLine,
                voteAverage = voteAverage,
                voteCount = voteCount
            )
        }
    }
}

interface MapDetailFilm {

    fun <T : Any> map(mapper: DetailFilmMapper<T>): T
}