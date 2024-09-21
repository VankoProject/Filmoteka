package com.kliachenko.search.presentation

import com.kliachenko.domain.FilmSearchDomain
import com.kliachenko.search.domain.LoadResult
import com.kliachenko.search.presentation.adapter.SearchUi

class BaseSearchLoadResultMapper(
    private val uiMapper: BaseSearchUiMapper,
) : LoadResult.Mapper<SearchUiState> {

    override fun mapSuccess(items: List<FilmSearchDomain>): SearchUiState {
        val films: List<Film> = items.map { it.map(uiMapper) }
        val result: SearchUi = SearchUi.Success(films)
        return SearchUiState.Success(listOf(result))
    }

    override fun mapError(message: String): SearchUiState {
        return SearchUiState.Error(message)
    }

    override fun mapEmpty(message: String): SearchUiState {
        return SearchUiState.Initial(message)
    }
}