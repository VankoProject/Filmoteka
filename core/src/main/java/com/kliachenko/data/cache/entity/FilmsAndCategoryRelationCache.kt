package com.kliachenko.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "films_and_category_table", primaryKeys = ["filmId", "categoryName", "pageNumber"])
data class FilmsAndCategoryRelationCache(

    @ColumnInfo(name = "film_id")
    val filmId: Int,

    @ColumnInfo(name = "category_name", index = true)
    val categoryName: String,

    @ColumnInfo(name = "page_number")
    val pageNumber: Int

)
