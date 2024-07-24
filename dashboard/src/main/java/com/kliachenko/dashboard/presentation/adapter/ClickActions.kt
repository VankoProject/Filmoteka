package com.kliachenko.dashboard.presentation.adapter

interface ClickActions: NavigateToDetail {

    fun retry()

    fun remove(item: DashboardUi)

    fun add(item: DashboardUi)
}

interface NavigateToDetail {
    fun navigate(filmId: Int)
}