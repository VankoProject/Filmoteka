package com.kliachenko.filmoteka.main

import com.kliachenko.core.Module
import com.kliachenko.filmoteka.di.Clear

class MainModule(
    private val clear: Clear
): Module<MainViewModel> {

    override fun provideViewModel() = MainViewModel(
        communication = MainCommunication.Base
    )
}