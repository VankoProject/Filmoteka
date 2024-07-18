package com.kliachenko.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_table")
data class FilmCache(

    @PrimaryKey
    @ColumnInfo(name = "id")
    private val id: Int,
    @ColumnInfo(name = "overview")
    private val overview: String,
    @ColumnInfo(name = "poster_path")
    private val posterPath: String,
    @ColumnInfo(name = "release_date")
    private val releaseDate: String,
    @ColumnInfo(name = "title")
    private val title: String,
    @ColumnInfo(name = "vote_average")
    private val voteAverage: Double,

)
