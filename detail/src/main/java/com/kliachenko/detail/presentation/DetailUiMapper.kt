package com.kliachenko.detail.presentation

import com.kliachenko.domain.DetailItem

class DetailUiMapper : DetailItem.Mapper<FilmDetailUi> {

    override fun mapItem(
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
    ) = FilmDetailUi.FilmDetail(
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
        voteCount = voteCount,
    )

}
