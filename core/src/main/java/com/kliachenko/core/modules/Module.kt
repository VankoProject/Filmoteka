package com.kliachenko.core.modules

import androidx.lifecycle.ViewModel

interface Module<T: ViewModel> {

    fun provideViewModel(): T
}