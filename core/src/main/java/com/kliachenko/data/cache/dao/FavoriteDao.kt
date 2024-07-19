package com.kliachenko.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kliachenko.data.cache.entity.FilmCache

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM film_table WHERE id IN (SELECT film_id FROM favorite_films_table)")
    suspend fun favorites(): List<FilmCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(filmId: Int)

    @Query("DELETE FROM favorite_films_table WHERE film_id = :filmId")
    suspend fun removeFromFavorite(filmId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_films_table WHERE film_id = :filmId)")
    suspend fun isFavorite(filmId: Int): Boolean

}