package com.kliachenko.dashboard.data

import com.kliachenko.core.HandleError
import com.kliachenko.dashboard.data.cache.CategoryCacheDataSource
import com.kliachenko.dashboard.data.cache.DashboardCacheDataSource
import com.kliachenko.dashboard.domain.DashboardRepository
import com.kliachenko.dashboard.domain.LoadResult
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
    private val handleError: HandleError<String>,
) : DashboardRepository {

    override suspend fun filmsByCategory(category: String, page: Int): LoadResult {
        val isEmptyCache = dashboardCacheDataSource.filmsByCategory(category, page).isEmpty()
        try {
            val films = if (isEmptyCache) {
                val cloudFilmResponse = cloudDataSource.loadFilms(category, page)
                val cloudFilms = cloudFilmResponse.results()
                val totalPages = cloudFilmResponse.totalPages()

                categoryCacheDataSource.save(CategoryCache(categoryName = category))
                val relationMapper = FilmsMapper.ToRelation.Base(category, page)
                cloudFilms.forEach { itemCloud ->
                    dashboardCacheDataSource.saveRelation(itemCloud.map(relationMapper))
                    dashboardCacheDataSource.save(itemCloud.map(mapToCache))
                }
                cloudFilms.map { itemCloud -> itemCloud.map(mapToDomain) }
            } else {
                dashboardCacheDataSource.filmsByCategory(category, page)
                    .map { itemCache -> itemCache.map(mapToDomain) }
            }
            val favoriteFilmIds = favoritesCacheDataSource.favoriteFilmsIds()
            return LoadResult.Success(films, favoriteFilmIds)
        } catch (e: Exception) {
            return LoadResult.Error(handleError.handle(e))
        }
    }

    override suspend fun addToFavorite(filmId: Int) {
        favoritesCacheDataSource.save(FavoriteCache(filmId = filmId))
    }

    override suspend fun removeFromFavorites(filmId: Int) {
        favoritesCacheDataSource.remove(filmId = filmId)
    }

}