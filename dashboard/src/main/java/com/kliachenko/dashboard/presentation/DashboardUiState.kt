package com.kliachenko.dashboard.presentation

import com.kliachenko.dashboard.presentation.adapter.DashboardUi
import com.kliachenko.dashboard.presentation.adapter.ShowList


interface DashboardUiState {

    fun updateAdapter(adapter: ShowList)

    fun updateFilmState(filmUi: DashboardUi): DashboardUiState = this

    fun addFilms(newFilms: FilmsList): DashboardUiState = this

    data class Error(private val message: String) : DashboardUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.show(listOf(DashboardUi.Error(message)))
        }
    }

    object Progress : DashboardUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.show(listOf(DashboardUi.Progress))
        }
    }

    object BottomProgress : DashboardUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.show(listOf(DashboardUi.BottomProgress))
        }
    }

    object NoData : DashboardUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.show(listOf(DashboardUi.NoData))
        }

    }

    data class FilmsList(private val filmsList: List<DashboardUi>) : DashboardUiState {

        override fun addFilms(newFilms: FilmsList): DashboardUiState {
            val updateList = filmsList.toMutableList().apply {
                addAll(newFilms.filmsList.filterNot { it in filmsList })
            }
            return FilmsList(updateList)
        }

        override fun updateFilmState(filmUi: DashboardUi): DashboardUiState {
            val newList = filmsList.map {
                if (it == filmUi) it.changeStatus()
                else it
            }
            return FilmsList(newList)
        }

        override fun updateAdapter(adapter: ShowList) {
            adapter.show(filmsList)
        }
    }

    object Empty : DashboardUiState {
        override fun updateAdapter(adapter: ShowList) = Unit
    }

}