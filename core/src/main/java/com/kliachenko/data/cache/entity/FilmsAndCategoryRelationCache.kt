package com.kliachenko.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "films_and_category_table", primaryKeys = ["filmId", "categoryName"])
data class FilmsAndCategoryRelationCache(

    @ColumnInfo(name = "filmId")
    val filmId: Int,

    @ColumnInfo(name = "categoryName", index = true)
    val categoryName: String,

)
