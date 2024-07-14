package com.kliachenko.dashboard.presentation

import com.kliachenko.dashboard.presentation.adapter.DashboardUi
import com.kliachenko.dashboard.presentation.adapter.ShowList


interface DashboardUiState {

    fun updateAdapter(adapter: ShowList)

    fun updateState(filmUi: DashboardUi): DashboardUiState = this

    fun changeFilmStatus(): DashboardUiState = this

    data class Error (private val message: String): DashboardUiState {
        override fun updateAdapter(adapter: ShowList) {

        }
    }

    data class Progress(private val message: String): DashboardUiState {
        override fun updateAdapter(adapter: ShowList) {

        }
    }

    data class FilmsList(private val filmsList: List<DashboardUi>): DashboardUiState {

        override fun updateState(filmUi: DashboardUi): DashboardUiState {
            return super.updateState(filmUi)
        }

        override fun changeFilmStatus(): DashboardUiState {
            return super.changeFilmStatus()
        }

        override fun updateAdapter(adapter: ShowList) {

        }

    }
}