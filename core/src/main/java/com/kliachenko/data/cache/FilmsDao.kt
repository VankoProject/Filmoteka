package com.kliachenko.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FilmsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFilm(films: FilmCache)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun deleteFilm(films: FilmCache)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRelation(relation: FilmsAndCategoryRelationCache)

    @Query("SELECT * FROM film_table WHERE id IN (SELECT filmId FROM films_and_category_table WHERE categoryName == :categoryName)")
    suspend fun filmsByCategory(categoryName: String): List<FilmCache>

    @Query("SELECT * FROM film_table WHERE id = :id")
    suspend fun film(id: Int): FilmCache

}