package com.kliachenko.core

import android.content.Context
import com.kliachenko.data.cloud.MakeService
import com.kliachenko.data.cloud.ProvideHttpLoggingInterceptor

interface Core {

    fun provideRunAsync(): RunAsync

    fun provideMakeService(): MakeService

    fun provideManageResources(): ManageResource

    class Base(
        private val context: Context,
        private val debug: Boolean,
    ) : Core {

        private val runAsync = RunAsync.Base()

        private val makeService = MakeService.Base(ProvideHttpLoggingInterceptor.Factory(debug))

        private val manageResource = ManageResource.Base(context)

        override fun provideRunAsync() = runAsync

        override fun provideMakeService() = makeService

        override fun provideManageResources() = manageResource

    }
}