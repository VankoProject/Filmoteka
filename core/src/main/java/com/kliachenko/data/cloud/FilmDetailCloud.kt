package com.kliachenko.data.cloud

import com.google.gson.annotations.SerializedName
import com.kliachenko.data.mapper.FilmDetailMapper
import com.kliachenko.data.mapper.MapFilmDetail

data class FilmDetailCloud(
    @SerializedName("id")
    val filmId: Int,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("production_countries")
    val productionCountries: List<Countries>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
) : MapFilmDetail {
    override fun <T : Any> map(mapper: FilmDetailMapper<T>): T {
        return mapper.map(
            filmId,
            adult,
            genres.map { it.name },
            homepage,
            productionCountries.map { it.name },
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

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
)

data class Countries(
    @SerializedName("iso_3166_1")
    val iso: String,
    @SerializedName("name")
    val name: String,
)
