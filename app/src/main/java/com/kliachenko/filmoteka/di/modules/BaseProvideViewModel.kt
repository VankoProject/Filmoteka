package com.kliachenko.filmoteka.di.modules

import androidx.lifecycle.ViewModel
import com.kliachenko.core.modules.ProvideViewModel

class BaseProvideViewModel(
    private val module: ProvideModule,
) : ProvideViewModel {

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
        module.module(viewModelClass).provideViewModel()
}