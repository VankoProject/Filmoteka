package com.kliachenko.core.modules

import android.content.Context
import com.kliachenko.data.cache.FilmsDataBase

interface FilmsDatabaseModule: DatabaseModule<FilmsDataBase> {

    private class Release(
        context: Context
    ): DatabaseModule.Abstract<FilmsDataBase>(
        context = context,
        clazz = FilmsDataBase::class.java,
        name = "films_database"
    ), FilmsDatabaseModule

    private class Debug(
        context: Context
    ): DatabaseModule.Abstract<FilmsDataBase>(
        context = context,
        clazz = FilmsDataBase::class.java,
        name = "films_database_debug"
    ), FilmsDatabaseModule

    class Factory(context: Context, debuggable: Boolean): FilmsDatabaseModule {

        private val module = if(debuggable) Debug(context) else Release(context)

        override fun database() = module.database()

    }
}