package com.kliachenko.data.cloud

import com.google.gson.annotations.SerializedName
import com.kliachenko.data.mapper.FilmSearchMapper
import com.kliachenko.data.mapper.MapSearchFilms
import com.kliachenko.domain.FilmSearchDomain

data class FilmsSearchResponse(
    @SerializedName("page")
    private val page: Int,
    @SerializedName("results")
    private val results: List<FilmSearchCloud>,
    @SerializedName("total_pages")
    private val totalPages: Int,
    @SerializedName("total_results")
    private val totalResults: Int,
) {
    fun <T : Any> map(mapper: FilmSearchMapper<T>): List<T> =
        results.map { it.map(mapper) }
}

data class FilmSearchCloud(
    @SerializedName("id")
    private val filmId: Int,
    @SerializedName("title")
    private val title: String,
    @SerializedName("poster_path")
    private val posterPath: String,
) : MapSearchFilms<FilmSearchDomain> {
    override fun <T : Any> map(mapper: FilmSearchMapper<T>): T {
        return mapper.map(filmId, title, posterPath)
    }

}
