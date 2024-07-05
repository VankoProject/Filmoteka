package com.kliachenko.filmoteka.di.modules

import androidx.lifecycle.ViewModel
import com.kliachenko.core.Module
import com.kliachenko.filmoteka.di.Clear
import com.kliachenko.filmoteka.main.MainModule
import com.kliachenko.filmoteka.main.MainViewModel

interface ProvideModule {

    fun <T: ViewModel> module(clazz: Class<T>): Module<T>

    @Suppress("UNCHECKED_CAST")
    class Base(
        private val clear: Clear,
    ): ProvideModule {

        override fun <T : ViewModel> module(clazz: Class<T>): Module<T> {
           return when(clazz) {
               MainViewModel::class.java -> MainModule(clear)
               else -> throw IllegalStateException("unknown viewModel $clazz")
           } as Module<T>
        }
    }
}