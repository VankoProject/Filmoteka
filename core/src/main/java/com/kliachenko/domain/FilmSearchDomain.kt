package com.kliachenko.domain

import com.kliachenko.data.mapper.FilmSearchMapper
import com.kliachenko.data.mapper.MapSearchFilms

interface SearchItem {

    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {
        fun mapItem(
            filmId: Int,
            title: String,
            posterPath: String,
        ): T
    }

}

data class FilmSearchDomain(
    private val filmId: Int,
    private val title: String,
    private val posterPath: String,
) : SearchItem, MapSearchFilms<FilmSearchDomain> {

    override fun <T : Any> map(mapper: SearchItem.Mapper<T>): T {
        return mapper.mapItem(filmId = filmId, title = title, posterPath = posterPath)
    }

    override fun <T : Any> map(mapper: FilmSearchMapper<T>): T {
        return mapper.map(filmId = filmId, title = title, posterPath = posterPath)
    }

}