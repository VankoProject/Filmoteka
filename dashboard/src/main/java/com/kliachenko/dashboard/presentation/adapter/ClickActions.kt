package com.kliachenko.dashboard.presentation.adapter

interface ClickActions {

    fun retry()

    fun changeStatus(item: DashboardUi)

    fun openDetail()
}