package com.kliachenko.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kliachenko.data.mapper.FilmsMapper
import com.kliachenko.data.mapper.MapFilms

@Entity(tableName = "film_table")
data class FilmCache(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    ) : MapFilms {
    override fun <T : Any> map(mapper: FilmsMapper<T>): T =
        mapper.map(id, overview, posterPath, releaseDate, title, voteAverage)
}
