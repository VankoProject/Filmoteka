package com.kliachenko.core

import android.content.Context

interface Core {

    fun provideRunAsync(): RunAsync

    class Base(
        private val context: Context,
    ) : Core {

        private val runAsync = RunAsync.Base()

        override fun provideRunAsync() = runAsync

    }
}