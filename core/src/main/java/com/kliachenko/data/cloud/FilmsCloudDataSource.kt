package com.kliachenko.data.cloud

import com.kliachenko.core.BaseCloudDataSource

interface FilmsCloudDataSource {

    suspend fun loadFilms(category: String): FilmsResponse

    class Base(
        private val service: FilmsService
    ): BaseCloudDataSource(), FilmsCloudDataSource {

        override suspend fun loadFilms(category: String): FilmsResponse =
            handleRequest(service.filmsByCategory(category))

    }
}