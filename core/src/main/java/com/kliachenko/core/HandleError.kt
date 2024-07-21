package com.kliachenko.core

import java.net.UnknownHostException

interface HandleError<T : Any> {

    fun handle(e: Exception): T

    class Base(private val manageResource: ManageResource) : HandleError<String> {

        override fun handle(e: Exception) =
            if (e is UnknownHostException)
                manageResource.noInternetConnection()
            else
                manageResource.serviceUnavailable()

    }
}