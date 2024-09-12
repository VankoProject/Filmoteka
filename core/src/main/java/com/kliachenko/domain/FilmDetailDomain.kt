package com.kliachenko.domain

interface DetailItem {

    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {
        fun mapItem(
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
    }
}


data class FilmDetailDomain(
    val filmId: Int,
    val adult: Boolean,
    val genres: List<String>,
    val homePage: String,
    val productionCountries: List<String>,
    val originalLanguage: String,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val tagline: String,
    val voteAverage: Double,
    val voteCount: Int,
) : DetailItem {

    fun id() = filmId

    override fun <T : Any> map(mapper: DetailItem.Mapper<T>): T {
        return mapper.mapItem(
            filmId,
            adult,
            genres,
            homePage,
            productionCountries,
            originalLanguage,
            title,
            overview,
            posterPath,
            releaseDate,
            runtime,
            tagline,
            voteAverage,
            voteCount
        )
    }
}