package com.kliachenko.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "detail_film_table")
data class FilmDetailCache(
    @ColumnInfo(name = "film_id")
    val filmId: Int,
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "genres")
    val genres: List<String>,
    @ColumnInfo(name = "home_page")
    val homePage: String,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "runtime")
    val runtime: Int,
    @ColumnInfo(name = "tagline")
    val tagline: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Int,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int,
)
