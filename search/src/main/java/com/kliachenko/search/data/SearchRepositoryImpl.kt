package com.kliachenko.search.data

import com.kliachenko.core.HandleError
import com.kliachenko.core.ManageResource
import com.kliachenko.data.cloud.FilmsCloudDataSource
import com.kliachenko.data.mapper.FilmSearchMapper
import com.kliachenko.domain.FilmSearchDomain
import com.kliachenko.search.data.cache.SearchHistoryDataSource
import com.kliachenko.search.domain.LoadResult
import com.kliachenko.search.domain.SearchRepository

class SearchRepositoryImpl(
    private val searchDataSource: SearchHistoryDataSource.Mutable,
    private val cloudDataSource: FilmsCloudDataSource.FilmsSearch,
    private val mapToCache: FilmSearchMapper.ToCache = FilmSearchMapper.ToCache.Base,
    private val mapToDomain: FilmSearchMapper.ToDomain = FilmSearchMapper.ToDomain.Base,
    private val handleError: HandleError<String>,
    private val manageResource: ManageResource.Search
) : SearchRepository {

    override suspend fun search(query: String): LoadResult {
        return try {
            val response = cloudDataSource.filmsSearch(query)
            val result = response.map(mapToDomain)
            if (result.isEmpty())
                LoadResult.Empty(manageResource.noFoundInfoText())
            else
                LoadResult.Success(response.map(mapToDomain))
        } catch (e: Exception) {
            LoadResult.Error(handleError.handle(e))
        }
    }

    override suspend fun loadHistory(): List<FilmSearchDomain> =
        searchDataSource.read().map { it.map(mapToDomain) }

    override suspend fun saveFilmToHistory(filmSearchDomain: FilmSearchDomain) {
        searchDataSource.save(filmSearchDomain.map(mapToCache))
    }
}