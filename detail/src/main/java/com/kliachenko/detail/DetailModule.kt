package com.kliachenko.detail

import com.kliachenko.core.Core
import com.kliachenko.core.modules.Clear
import com.kliachenko.core.modules.Module
import com.kliachenko.detail.presentation.DetailViewModel

class DetailModule(
    private val core: Core,
    private val clear: Clear
): Module<DetailViewModel> {
    override fun provideViewModel(): DetailViewModel {
        return DetailViewModel(
            runAsync = core.provideRunAsync()
        )
    }
}