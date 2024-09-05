package com.kliachenko.core

import android.content.Context

interface ManageResource {

    fun stringResource(id: Int): String

    fun noInternetConnection(): String

    fun serviceUnavailable(): String

    class Base(private val context: Context) : ManageResource {

        override fun stringResource(id: Int) = context.getString(id)

        override fun noInternetConnection() = context.resources.getString(R.string.no_internet_connection)

        override fun serviceUnavailable() =
            context.resources.getString(R.string.service_unavailable)

    }
}