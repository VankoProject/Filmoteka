package com.kliachenko.dashboard.data.cache

import com.kliachenko.data.cache.dao.CategoryDao

interface CategoryCacheDataSource {

    interface Save {
        suspend fun save(category: String)
    }

    interface Read {
        suspend fun category(category: String): String
    }

    interface Mutable: Save, Read

    class Base(private val categoryDao: CategoryDao): Mutable {

        override suspend fun save(category: String) {
            categoryDao.save(category)
        }

        override suspend fun category(category: String) = categoryDao.category(category)

    }

}