package com.kliachenko.core

import android.content.Context
import com.kliachenko.core.modules.FilmsDatabaseModule
import com.kliachenko.data.cache.FilmsDataBase
import com.kliachenko.data.cloud.MakeService
import com.kliachenko.data.cloud.ProvideHttpLoggingInterceptor

interface Core {

    fun provideRunAsync(): RunAsync

    fun provideMakeService(): MakeService

    fun provideManageResources(): ManageResource

    fun provideDataBase(): FilmsDataBase

    class Base(
        context: Context,
        debug: Boolean,
    ) : Core {

        private val filmsDatabaseModule: FilmsDatabaseModule =
            FilmsDatabaseModule.Factory(context, debug)

        private val runAsync = RunAsync.Base()

        private val makeService = MakeService.Base(ProvideHttpLoggingInterceptor.Factory(debug))

        private val manageResource = ManageResource.Base(context)

        override fun provideRunAsync() = runAsync

        override fun provideMakeService() = makeService

        override fun provideManageResources() = manageResource

        override fun provideDataBase() = filmsDatabaseModule.database()

    }
}