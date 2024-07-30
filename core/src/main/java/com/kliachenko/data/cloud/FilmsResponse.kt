package com.kliachenko.data.cloud

import com.google.gson.annotations.SerializedName
import com.kliachenko.data.mapper.FilmsMapper
import com.kliachenko.data.mapper.MapFilms

data class FilmsResponse(

    @SerializedName("page")
    private val page: Int,
    @SerializedName("results")
    private val results: List<FilmCloud>,
    @SerializedName("total_pages")
    private val totalPages: Int,
    @SerializedName("total_results")
    private val totalResults: Int
) {
    fun <T: Any> map(mapper: FilmsMapper<T>): List<T> = results.map {
        it.map(mapper)
    }

    fun results(): List<FilmCloud> = results

    fun currentPage(): Int = page

    fun totalPages(): Int = totalPages
}

data class FilmCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("overview")
    private val overview: String,
    @SerializedName ("poster_path")
    private val posterPath: String,
    @SerializedName("release_date")
    private val releaseDate: String,
    @SerializedName("title")
    private val title: String,
    @SerializedName("vote_average")
    private val voteAverage: Double,
): MapFilms {
    override fun <T : Any> map(mapper: FilmsMapper<T>): T =
        mapper.map(id, overview, posterPath, releaseDate, title, voteAverage)

}
