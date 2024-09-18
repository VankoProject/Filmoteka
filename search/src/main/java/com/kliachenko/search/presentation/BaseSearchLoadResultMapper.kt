package com.kliachenko.search.presentation

import com.kliachenko.domain.FilmSearchDomain
import com.kliachenko.search.domain.LoadResult

class BaseSearchLoadResultMapper(
    private val uiMapper: BaseSearchUiMapper
): LoadResult.Mapper<SearchUiState> {

    override fun mapSuccess(items: List<FilmSearchDomain>): SearchUiState {
        val uiItems = items.map { it.map(uiMapper) }
        return SearchUiState.Success(uiItems)
    }

    override fun mapError(message: String): SearchUiState {
        return SearchUiState.Error(message)
    }

    override fun mapEmpty(): SearchUiState {
        return SearchUiState.Empty
    }
}