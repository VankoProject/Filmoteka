package com.kliachenko.dashboard.presentation

import com.kliachenko.core.BaseViewModel
import com.kliachenko.core.RunAsync
import com.kliachenko.core.modules.Clear

class DashboardViewModel(
    private val clear: Clear,
    runAsync: RunAsync) : BaseViewModel(runAsync) {
}