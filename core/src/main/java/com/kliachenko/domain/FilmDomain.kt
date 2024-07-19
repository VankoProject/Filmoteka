package com.kliachenko.domain

import com.kliachenko.data.mapper.FilmsMapper
import com.kliachenko.data.mapper.MapFilms

data class FilmDomain(
    private val id: Int,
    private val overview: String,
    private val posterPath: String,
    private val releaseDate: String,
    private val title: String,
    private val voteAverage: Double,
) : MapFilms {
    override fun <T : Any> map(mapper: FilmsMapper<T>): T =
        mapper.map(id, overview, posterPath, releaseDate, title, voteAverage)
}
