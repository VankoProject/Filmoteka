package com.kliachenko.dashboard.presentation

import com.kliachenko.dashboard.domain.LoadResult
import com.kliachenko.domain.FilmDomain

class BaseDashboardResultMapper(
    private val mapper: BaseDashboardUiMapper,
) : LoadResult.Mapper<DashboardUiState> {

    override fun mapSuccess(items: List<FilmDomain>, favoriteFilmIds: Set<Int>): DashboardUiState {
       val uiItems = items.map {
           filmDomain -> filmDomain.map(mapper, favoriteFilmIds.contains(filmDomain.id()))
       }
        return DashboardUiState.FilmsList(uiItems)
    }

    override fun mapError(message: String): DashboardUiState {
        return DashboardUiState.Error(message)
    }

    override fun mapEmpty(): DashboardUiState {
        return DashboardUiState.Empty
    }

    override fun mapNoData(message: String): DashboardUiState {
        return DashboardUiState.NoData
    }
}