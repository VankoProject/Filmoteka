package com.kliachenko.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kliachenko.data.cache.dao.CategoryDao
import com.kliachenko.data.cache.dao.FavoriteDao
import com.kliachenko.data.cache.dao.FilmsDao
import com.kliachenko.data.cache.entity.CategoryCache
import com.kliachenko.data.cache.entity.FavoriteCache
import com.kliachenko.data.cache.entity.FilmCache
import com.kliachenko.data.cache.entity.FilmDetailCache
import com.kliachenko.data.cache.entity.FilmsAndCategoryRelationCache

@Database(
    entities = [
        FilmCache::class,
        CategoryCache::class,
        FavoriteCache::class,
        FilmsAndCategoryRelationCache::class,
        FilmDetailCache::class],
    version = 1,
    exportSchema = false
)
abstract class FilmsDataBase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun filmsDao(): FilmsDao

    abstract fun favoriteDao(): FavoriteDao

}