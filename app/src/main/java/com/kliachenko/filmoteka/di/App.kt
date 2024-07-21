package com.kliachenko.filmoteka.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.kliachenko.core.Core
import com.kliachenko.core.LoadPicEngine
import com.kliachenko.core.ProvideLoadPicEngine
import com.kliachenko.core.modules.Clear
import com.kliachenko.core.modules.ProvideViewModel
import com.kliachenko.filmoteka.di.modules.BaseProvideViewModel
import com.kliachenko.filmoteka.di.modules.ProvideModule

class App : Application(), ProvideViewModel, ProvideLoadPicEngine {

    private lateinit var viewmodelFactory: ProvideViewModel.Factory

    override fun onCreate() {
        super.onCreate()
        val clear = object : Clear {
            override fun clear(viewModelClass: Class<out ViewModel>) {
                viewmodelFactory.clear(viewModelClass)
            }
        }

        viewmodelFactory = ProvideViewModel.Factory(
            BaseProvideViewModel(
                ProvideModule.Base(Core.Base(this, true), clear)
            )
        )
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
        viewmodelFactory.viewModel(viewModelClass)

    override fun picEngine() = LoadPicEngine.Base()

}