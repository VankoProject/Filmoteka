package com.kliachenko.dashboard.presentation


interface DashboardUiState {

    fun updateAdapter(adapter: ShowList)

    fun updateState(filmUi: FilmUi): DashboardUiState = this

    fun changeFilmStatus(): DashboardUiState = this

    data class Error (private val message: String): DashboardUiState {
        override fun updateAdapter(adapter: ShowList) {

        }
    }

    data class Progress(private val message: String): DashboardUiState {
        override fun updateAdapter(adapter: ShowList) {
            TODO("Not yet implemented")
        }
    }

    data class FilmsList(private val filmsList: List<FilmUi>): DashboardUiState {

        override fun updateState(filmUi: FilmUi): DashboardUiState {
            return super.updateState(filmUi)
        }

        override fun changeFilmStatus(): DashboardUiState {
            return super.changeFilmStatus()
        }

        override fun updateAdapter(adapter: ShowList) {
            TODO("Not yet implemented")
        }

    }
}