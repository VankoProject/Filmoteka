package com.kliachenko.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kliachenko.data.cache.entity.FilmSearchHistoryCache

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFilmToHistory(searchHistoryFilm: FilmSearchHistoryCache)

    @Query("SELECT * FROM history_films_table ORDER by addedAt DESC")
    suspend fun allHistoryFilms(): List<FilmSearchHistoryCache>
}