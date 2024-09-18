package com.kliachenko.search.presentation

import com.kliachenko.domain.SearchItem
import com.kliachenko.search.presentation.adapter.SearchUi

class BaseSearchUiMapper : SearchItem.Mapper<SearchUi> {

    override fun mapItem(filmId: Int, title: String, posterPath: String): SearchUi {
        return SearchUi.Success(filmId = filmId, title = title, imageUrl = URL_POSTER + posterPath)
    }

    companion object {
        private const val URL_POSTER = "https://image.tmdb.org/t/p/w500"
    }

}