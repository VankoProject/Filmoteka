package com.kliachenko.domain

data class FilmDomain(
    private val id: Int,
    private val overview: String,
    private val posterPath: String,
    private val releaseDate: String,
    private val title: String,
    private val voteAverage: Double,
)
