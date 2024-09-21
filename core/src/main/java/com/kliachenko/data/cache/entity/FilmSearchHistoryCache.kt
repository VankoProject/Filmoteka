package com.kliachenko.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kliachenko.data.mapper.FilmSearchMapper
import com.kliachenko.data.mapper.MapSearchFilms
import com.kliachenko.domain.FilmSearchDomain

@Entity(tableName = "history_films_table")
data class FilmSearchHistoryCache(
    @PrimaryKey
    @ColumnInfo(name = "film_id")
    val filmId: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    val addedAt: Long = System.currentTimeMillis()
) : MapSearchFilms<FilmSearchDomain> {

    override fun <T : Any> map(mapper: FilmSearchMapper<T>): T {
        return mapper.map(filmId, title, posterPath)
    }

}
