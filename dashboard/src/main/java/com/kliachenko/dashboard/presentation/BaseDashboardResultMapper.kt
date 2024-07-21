package com.kliachenko.dashboard.presentation

import com.kliachenko.dashboard.domain.DashboardResult
import com.kliachenko.domain.FilmDomain

class BaseDashboardResultMapper(
    private val mapper: BaseDashboardItemMapper,
) : DashboardResult.Mapper<DashboardUiState> {

    override fun mapSuccess(items: List<FilmDomain>): DashboardUiState {
        return DashboardUiState.FilmsList(items.map { it.map(mapper) })
    }

    override fun mapError(message: String): DashboardUiState {
        return DashboardUiState.Error(message)
    }

    override fun mapEmpty(): DashboardUiState {
        return DashboardUiState.Empty
    }
}