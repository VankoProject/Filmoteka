package com.kliachenko.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_films_table")
data class FavoriteCache(
    @PrimaryKey
    @ColumnInfo(name = "film_id")
    val filmId: Int,
)