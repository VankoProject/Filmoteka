package com.kliachenko.filmoteka.di

import androidx.lifecycle.ViewModel
import com.kliachenko.filmoteka.di.modules.ProvideModule

class BaseProvideViewModel(
    private val provideModule: ProvideModule
): ProvideViewModel {

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
        provideModule.module(viewModelClass).provideViewModel()

}