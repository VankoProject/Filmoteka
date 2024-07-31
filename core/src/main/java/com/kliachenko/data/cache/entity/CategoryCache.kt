package com.kliachenko.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kliachenko.data.mapper.CategoryMapper
import com.kliachenko.data.mapper.MapCategory

@Entity(tableName = "category_table")
data class CategoryCache(
    @PrimaryKey
    @ColumnInfo(name = "category_name")
    val categoryName: String,
    @ColumnInfo(name = "total_pages")
    val totalPages: Int
) : MapCategory {

    override fun <T : Any> map(mapper: CategoryMapper<T>): T = mapper.map(categoryName)

}
