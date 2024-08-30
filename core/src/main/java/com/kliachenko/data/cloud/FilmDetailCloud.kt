package com.kliachenko.data.cloud

import com.google.gson.annotations.SerializedName
import com.kliachenko.data.mapper.DetailFilmMapper
import com.kliachenko.data.mapper.MapDetailFilm

data class FilmDetailCloud(
    @SerializedName("id")
    val filmId: Int,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("homepage")
    val homepage: String,
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
) : MapDetailFilm {
    override fun <T : Any> map(mapper: DetailFilmMapper<T>): T {
        return mapper.map(
            filmId,
            adult,
            genres.map { it.name },
            homepage,
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
