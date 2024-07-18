package com.kliachenko.core.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

interface DatabaseModule<T: RoomDatabase> {

    fun database(): T

    abstract class Abstract<T: RoomDatabase>(
        private val context: Context,
        private val clazz: Class<T>,
        private val name: String
    ): DatabaseModule<T> {

        private val database by lazy {
            Room.databaseBuilder(context, clazz, name).build()
        }

        override fun database(): T  = database
    }
}