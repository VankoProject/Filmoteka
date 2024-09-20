package com.kliachenko.search.presentation

import com.kliachenko.domain.SearchItem

class BaseSearchUiMapper : SearchItem.Mapper<Film> {

    override fun mapItem(filmId: Int, title: String, posterPath: String): Film {
        return Film(filmId = filmId, title = title, imageUrl = URL_POSTER + posterPath)
    }

    companion object {
        private const val URL_POSTER = "https://image.tmdb.org/t/p/w500"
    }

}

data class Film(
    val filmId: Int,
    val title: String,
    val imageUrl: String
)