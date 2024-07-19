package com.kliachenko.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CategoryDao {

    @Query("SELECT category_name FROM category_table")
    suspend fun categories(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(category: String)

    @Query("SELECT category_name FROM category_table WHERE category_name = :tabCategory")
    suspend fun category(tabCategory: String): String

}