package com.kliachenko.core

import androidx.lifecycle.ViewModel

interface Module<T: ViewModel> {

    fun provideViewModel(): T
}