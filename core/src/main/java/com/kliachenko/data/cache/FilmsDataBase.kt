package com.kliachenko.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kliachenko.core.TypeConverter
import com.kliachenko.data.cache.dao.CategoryDao
import com.kliachenko.data.cache.dao.FavoriteDao
import com.kliachenko.data.cache.dao.FilmsDao
import com.kliachenko.data.cache.dao.SearchHistoryDao
import com.kliachenko.data.cache.entity.CategoryCache
import com.kliachenko.data.cache.entity.FavoriteCache
import com.kliachenko.data.cache.entity.FilmDashboardCache
import com.kliachenko.data.cache.entity.FilmDetailCache
import com.kliachenko.data.cache.entity.FilmSearchHistoryCache
import com.kliachenko.data.cache.entity.FilmsAndCategoryRelationCache

@Database(
    entities = [
        FilmDashboardCache::class,
        CategoryCache::class,
        FavoriteCache::class,
        FilmsAndCategoryRelationCache::class,
        FilmDetailCache::class,
        FilmSearchHistoryCache::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [TypeConverter::class])
abstract class FilmsDataBase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun filmsDao(): FilmsDao

    abstract fun favoriteDao(): FavoriteDao

    abstract fun searchHistoryDao(): SearchHistoryDao

}