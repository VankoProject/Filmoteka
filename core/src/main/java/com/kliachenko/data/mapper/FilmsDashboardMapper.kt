package com.kliachenko.data.mapper

import com.kliachenko.data.cache.entity.FavoriteCache
import com.kliachenko.data.cache.entity.FilmDashboardCache
import com.kliachenko.data.cache.entity.FilmsAndCategoryRelationCache
import com.kliachenko.domain.FilmDashboardDomain

interface FilmsDashboardMapper<T : Any> {

    fun map(
        id: Int,
        overview: String,
        posterPath: String,
        releaseDate: String,
        title: String,
        voteAverage: Double,
    ): T

    interface ToCache : FilmsDashboardMapper<FilmDashboardCache> {

        object Base : ToCache {
            override fun map(
                id: Int,
                overview: String,
                posterPath: String,
                releaseDate: String,
                title: String,
                voteAverage: Double,
            ) = FilmDashboardCache(
                id = id,
                overview = overview,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage,
            )
        }
    }

    interface ToDomain: FilmsDashboardMapper<FilmDashboardDomain> {

        object Base: ToDomain {
            override fun map(
                id: Int,
                overview: String,
                posterPath: String,
                releaseDate: String,
                title: String,
                voteAverage: Double,
            ): FilmDashboardDomain = FilmDashboardDomain (
                id = id,
                overview = overview,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage,
            )
        }
    }

    interface ToFavorite: FilmsDashboardMapper<FavoriteCache> {

        object Base: ToFavorite {
            override fun map(
                id: Int,
                overview: String,
                posterPath: String,
                releaseDate: String,
                title: String,
                voteAverage: Double,
            ) : FavoriteCache = FavoriteCache(
                filmId = id
            )
        }
    }

    interface ToRelation: FilmsDashboardMapper<FilmsAndCategoryRelationCache> {

        class Base(private val categoryName: String, private val page: Int): ToRelation {
            override fun map(
                id: Int,
                overview: String,
                posterPath: String,
                releaseDate: String,
                title: String,
                voteAverage: Double,
            ): FilmsAndCategoryRelationCache = FilmsAndCategoryRelationCache(
                filmId = id,
                categoryName = categoryName,
                pageNumber = page
            )
        }
    }
}

interface MapDashboardFilms {

    fun <T: Any> map(mapper: FilmsDashboardMapper<T>): T
}