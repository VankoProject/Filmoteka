package com.kliachenko.data.mapper

import com.kliachenko.data.cache.entity.FilmSearchHistoryCache
import com.kliachenko.domain.FilmSearchDomain

interface FilmSearchMapper<T : Any> {

    fun map(
        filmId: Int,
        title: String,
        posterPath: String,
    ): T

    interface ToCache : FilmSearchMapper<FilmSearchHistoryCache> {

        object Base : ToCache {
            override fun map(filmId: Int, title: String, posterPath: String): FilmSearchHistoryCache {
                return FilmSearchHistoryCache(
                    filmId = filmId,
                    title = title,
                    posterPath = posterPath
                )
            }
        }
    }

    interface ToDomain : FilmSearchMapper<FilmSearchDomain> {

        object Base : ToDomain {
            override fun map(filmId: Int, title: String, posterPath: String): FilmSearchDomain {
                return FilmSearchDomain(
                    filmId = filmId,
                    title = title,
                    posterPath = posterPath
                )
            }
        }
    }
}

interface MapSearchFilms<T : Any> {

    fun <T : Any> map(mapper: FilmSearchMapper<T>): T
}