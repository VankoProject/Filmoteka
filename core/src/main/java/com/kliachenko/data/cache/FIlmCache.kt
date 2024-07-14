package com.kliachenko.data.cache

data class FilmCache(

    private val query: String,

    private val id: Int,

    private val overview: String,

    private val posterPath: String,

    private val releaseDate: String,

    private val title: String,

    private val voteAverage: Double,

    private val isFavorite: Boolean
)
