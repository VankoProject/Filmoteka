package com.kliachenko.filmoteka.main

import com.kliachenko.core.modules.Clear
import com.kliachenko.core.modules.Module

class MainModule(
    private val clear: Clear
): Module<MainViewModel> {

    override fun provideViewModel() = MainViewModel(
        communication = MainCommunication.Base
    )
}