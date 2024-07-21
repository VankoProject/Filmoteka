package com.kliachenko.dashboard.presentation

import com.kliachenko.dashboard.presentation.adapter.DashboardUi
import com.kliachenko.dashboard.presentation.adapter.ShowList


interface DashboardUiState {

    fun updateAdapter(adapter: ShowList)

    fun updateFilmState(filmUi: DashboardUi): DashboardUiState = this

    data class Error (private val message: String): DashboardUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.show(listOf(DashboardUi.Error(message)))
        }
    }

    object Progress: DashboardUiState {
        override fun updateAdapter(adapter: ShowList) {
            adapter.show(listOf(DashboardUi.Progress))
        }
    }

    data class FilmsList(private val filmsList: List<DashboardUi>): DashboardUiState {

        override fun updateFilmState(filmUi: DashboardUi): DashboardUiState {
            val newList = filmsList.toMutableList()
            val newFilmIndex = filmsList.indexOf(filmUi)
            newList[newFilmIndex] = filmUi.changeStatus()
            filmsList.find { it.isFavorite() }?.let { currentlyStatus->
                if(filmUi != currentlyStatus) {
                    val index = filmsList.indexOf(currentlyStatus)
                    newList[index] = currentlyStatus.changeStatus()
                }
            }
            return FilmsList(newList)
        }

        override fun updateAdapter(adapter: ShowList) {
            adapter.show(filmsList)
        }

    }

    object Empty: DashboardUiState {
        override fun updateAdapter(adapter: ShowList) = Unit
    }

}