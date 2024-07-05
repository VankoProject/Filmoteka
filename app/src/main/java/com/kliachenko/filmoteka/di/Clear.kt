package com.kliachenko.filmoteka.di

import androidx.lifecycle.ViewModel

interface Clear {

    fun clear(viewModelClass: Class<out ViewModel>)
}