package com.kliachenko.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FilmCache::class, CategoryCache::class, FavoriteCache::class, FilmsAndCategoryRelationCache::class],
    version = 1,
    exportSchema = false
)
abstract class FilmsDataBase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun filmsDao(): FilmsDao

    abstract fun favoriteDao(): FavoriteDao

}