package com.kliachenko.dashboard

import com.kliachenko.core.Core
import com.kliachenko.core.modules.Clear
import com.kliachenko.core.modules.Module
import com.kliachenko.dashboard.presentation.DashboardViewModel

class DashboardModule(
    private val core: Core,
    private val clear: Clear,
) : Module<DashboardViewModel> {

    override fun provideViewModel(): DashboardViewModel {
        return DashboardViewModel(clear, core.provideRunAsync())
    }
}