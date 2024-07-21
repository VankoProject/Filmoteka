package com.kliachenko.dashboard.data

import com.kliachenko.core.HandleError
import com.kliachenko.dashboard.data.cache.CategoryCacheDataSource
import com.kliachenko.dashboard.data.cache.DashboardCacheDataSource
import com.kliachenko.dashboard.domain.DashboardRepository
import com.kliachenko.dashboard.domain.DashboardResult
import com.kliachenko.data.cache.FavoritesCacheDataSource
import com.kliachenko.data.cache.entity.CategoryCache
import com.kliachenko.data.cache.entity.FavoriteCache
import com.kliachenko.data.cloud.FilmsCloudDataSource
import com.kliachenko.data.mapper.FilmsMapper

class DashboardRepositoryImpl(
    private val cloudDataSource: FilmsCloudDataSource,
    private val dashboardCacheDataSource: DashboardCacheDataSource.Mutable,
    private val favoritesCacheDataSource: FavoritesCacheDataSource.Mutable,
    private val categoryCacheDataSource: CategoryCacheDataSource.Mutable,
    private val mapToCache: FilmsMapper.ToCache = FilmsMapper.ToCache.Base,
    private val mapToDomain: FilmsMapper.ToDomain = FilmsMapper.ToDomain.Base,
    private val handleError: HandleError<String>
) : DashboardRepository {

    override suspend fun filmsByCategory(category: String): DashboardResult {
        val isEmptyCache = dashboardCacheDataSource.filmsByCategory(category).isEmpty()
        try {
            if (isEmptyCache) {
                val films = cloudDataSource.loadFilms(category).results()
                categoryCacheDataSource.save(CategoryCache(categoryName = category))
                val relationMapper = FilmsMapper.ToRelation.Base(category)
                val result = films.map { itemCloud ->
                    dashboardCacheDataSource.saveRelation(itemCloud.map(relationMapper))
                    dashboardCacheDataSource.save(itemCloud.map(mapToCache))
                    itemCloud.map(mapToDomain)
                }
                return DashboardResult.Success(result)
            } else {
                val cache = dashboardCacheDataSource.filmsByCategory(category)
                val result = cache.map { itemCache ->
                    itemCache.map(mapToDomain)
                }
                return DashboardResult.Success(result)
            }
        } catch (e: Exception) {
            return DashboardResult.Error(handleError.handle(e))
        }
    }


    override suspend fun addToFavorite(filmId: Int) {
        favoritesCacheDataSource.save(FavoriteCache(filmId = filmId))
    }

    override suspend fun removeFromFavorites(filmId: Int) {
        favoritesCacheDataSource.remove(filmId = filmId)
    }
}