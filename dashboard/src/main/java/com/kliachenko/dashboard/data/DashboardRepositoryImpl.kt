package com.kliachenko.dashboard.data

import android.util.Log
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
import com.kliachenko.domain.FilmDomain

class DashboardRepositoryImpl(
    private val cloudDataSource: FilmsCloudDataSource,
    private val dashboardCacheDataSource: DashboardCacheDataSource.Mutable,
    private val favoritesCacheDataSource: FavoritesCacheDataSource.Mutable,
    private val categoryCacheDataSource: CategoryCacheDataSource.Mutable,
    private val mapToCache: FilmsMapper.ToCache = FilmsMapper.ToCache.Base,
    private val mapToDomain: FilmsMapper.ToDomain = FilmsMapper.ToDomain.Base,
    private val handleError: HandleError<String>,
) : DashboardRepository {

    override suspend fun filmsByCategoryAndPages(category: String, page: Int): LoadResult {
        return try {
            val cachedData =
                dashboardCacheDataSource.filmsByCategoryAndPage(category, page)
            val favoriteFilmsIds = favoritesCacheDataSource.favoriteFilmsIds()
            if (cachedData.isNotEmpty()) {
                LoadResult.Success(cachedData.map { it.map(mapToDomain) }, favoriteFilmsIds)
            } else {
                val response = cloudDataSource.loadFilms(category, page)
                val totalPages = response.totalPages()
                val cloudFilms = response.results()
                val relationMapper = FilmsMapper.ToRelation.Base(category, page)
                categoryCacheDataSource.save(
                    CategoryCache(
                        categoryName = category,
                        totalPages = totalPages
                    )
                )
                cloudFilms.forEach { filmItem ->
                    dashboardCacheDataSource.saveRelation(filmItem.map(relationMapper))
                    dashboardCacheDataSource.save(filmItem.map(mapToCache))
                }
                val films = cloudFilms.map { it.map(mapToDomain) }
                Log.d("Filmoteka", "repository load: $films and $favoriteFilmsIds")
                LoadResult.Success(films, favoriteFilmsIds)
            }
        } catch (e: Exception) {
            LoadResult.Error(handleError.handle(e))
        }
    }

    override suspend fun numbersFilmsByCategory(category: String): Int =
        dashboardCacheDataSource.filmsByCategory(category).size

    override suspend fun allCachedFilmsByCategory(category: String): List<FilmDomain> {
        return dashboardCacheDataSource.filmsByCategory(category).map { it.map(mapToDomain) }
    }

    override suspend fun allFilmsByCategory(category: String): LoadResult {
        val favoriteFilmsIds = favoritesCacheDataSource.favoriteFilmsIds()
        return LoadResult.Success(
            dashboardCacheDataSource.filmsByCategory(category).map { it.map(mapToDomain) },
            favoriteFilmsIds
        )
    }


    override suspend fun addToFavorite(filmId: Int) {
        favoritesCacheDataSource.save(FavoriteCache(filmId = filmId))
    }

    override suspend fun removeFromFavorites(filmId: Int) {
        favoritesCacheDataSource.remove(filmId = filmId)
    }

    override suspend fun totalPages(category: String): Int {
        return categoryCacheDataSource.category(category)
    }

}