package com.kliachenko.detail.presentation

import com.kliachenko.detail.domain.LoadResult
import com.kliachenko.domain.FilmDetailDomain

class BaseDetailResultMapper(
    private val uiMapper: DetailUiMapper,
) : LoadResult.Mapper<DetailUiState> {

    override fun mapSuccess(item: FilmDetailDomain, isFavorite: Boolean): DetailUiState {
        val result = item.map(uiMapper)
        return DetailUiState.Success(result, isFavorite)
    }

    override fun mapError(message: String): DetailUiState {
        return DetailUiState.Error(message)
    }

    override fun mapEmpty(): DetailUiState {
        return DetailUiState.Empty
    }
}