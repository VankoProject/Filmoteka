package com.kliachenko.filmoteka.di.modules

import androidx.lifecycle.ViewModel
import com.kliachenko.core.Core
import com.kliachenko.core.modules.Clear
import com.kliachenko.core.modules.Module
import com.kliachenko.dashboard.DashboardModule
import com.kliachenko.dashboard.presentation.DashboardViewModel
import com.kliachenko.detail.DetailModule
import com.kliachenko.detail.presentation.DetailViewModel
import com.kliachenko.filmoteka.main.MainModule
import com.kliachenko.filmoteka.main.MainViewModel

interface ProvideModule {

    fun <T: ViewModel> module(clazz: Class<T>): Module<T>

    @Suppress("UNCHECKED_CAST")
    class Base(
        private val core: Core,
        private val clear: Clear,
    ): ProvideModule {

        override fun <T : ViewModel> module(clazz: Class<T>): Module<T> {
           return when(clazz) {
               MainViewModel::class.java -> MainModule(clear)
               DashboardViewModel::class.java -> DashboardModule(core, clear)
               DetailViewModel::class.java -> DetailModule(core, clear)
               else -> throw IllegalStateException("unknown viewModel $clazz")
           } as Module<T>
        }
    }
}