package com.kliachenko.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kliachenko.data.cache.entity.FilmCache
import com.kliachenko.data.cache.entity.FilmDetailCache
import com.kliachenko.data.cache.entity.FilmsAndCategoryRelationCache

@Dao
interface FilmsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFilm(film: FilmCache)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRelation(relation: FilmsAndCategoryRelationCache)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDetailFilm(detailCache: FilmDetailCache)

    @Query("SELECT * FROM film_table WHERE id IN (SELECT film_id FROM films_and_category_table WHERE category_name == :categoryName AND page_number == :pageNumber)")
    suspend fun byCategoryAndPages(categoryName: String, pageNumber: Int): List<FilmCache>

    @Query("SELECT * FROM film_table WHERE id IN (SELECT film_id FROM films_and_category_table WHERE category_name == :categoryName)")
    suspend fun byCategory(categoryName: String): List<FilmCache>

    @Query("SELECT * FROM film_table WHERE id = :id")
    suspend fun film(id: Int): FilmCache

    @Query("SELECT * FROM detail_film_table WHERE film_id = :filmId")
    suspend fun detailFavoriteFilm(filmId: Int): FilmDetailCache

}