package com.kliachenko.core.navigation


interface NavigationActions {

    interface FromDashboard: NavigationActions {
        fun navigateFromDashboardToDetail(filmId: Int, filmTitle: String)
    }

    interface FromSearch: NavigationActions {
        fun navigateFromSearchToDetail(filmId: Int, filmTitle: String)
    }

    interface Mutable: FromDashboard, FromSearch

}