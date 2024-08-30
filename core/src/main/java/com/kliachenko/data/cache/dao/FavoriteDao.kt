package com.kliachenko.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kliachenko.data.cache.entity.FavoriteCache
import com.kliachenko.data.cache.entity.FilmCache
import com.kliachenko.data.cache.entity.FilmDetailCache

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM film_table WHERE id IN (SELECT film_id FROM favorite_films_table)")
    suspend fun favorites(): List<FilmCache>

    @Query("SELECT * FROM detail_film_table WHERE film_id = :filmId")
    suspend fun detailFavoriteFilm(filmId: Int): FilmDetailCache

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(filmId: FavoriteCache)

    @Query("DELETE FROM favorite_films_table WHERE film_id = :filmId")
    suspend fun removeFromFavorite(filmId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_films_table WHERE film_id = :filmId)")
    suspend fun isFavorite(filmId: Int): Boolean

    @Query("SELECT film_id FROM favorite_films_table")
    suspend fun favoriteFilmsIds(): List<Int>

}