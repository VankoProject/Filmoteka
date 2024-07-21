package com.kliachenko.dashboard.data.cache

import com.kliachenko.data.cache.dao.CategoryDao
import com.kliachenko.data.cache.entity.CategoryCache

interface CategoryCacheDataSource {

    interface Save {
        suspend fun save(category: CategoryCache)
    }

    interface Read {
        suspend fun category(category: String): String
    }

    interface Mutable: Save, Read

    class Base(private val categoryDao: CategoryDao): Mutable {

        override suspend fun save(category: CategoryCache) {
            categoryDao.save(category)
        }

        override suspend fun category(category: String) = categoryDao.category(category)

    }

}