package com.kliachenko.data.mapper

interface CategoryMapper<T: Any> {

    fun map(category: String): T

    interface ToCache: CategoryMapper<String> {

        class Base: ToCache {
            override fun map(category: String): String {
                return category
            }

        }
    }
}

interface MapCategory {
    fun <T: Any> map(mapper: CategoryMapper<T>): T
}