package com.kliachenko.data.cloud

import okhttp3.logging.HttpLoggingInterceptor

interface ProvideHttpLoggingInterceptor {

    fun httpLoggingInterceptor(): HttpLoggingInterceptor

    abstract class Abstract (
        private val logLevel: HttpLoggingInterceptor.Level
    ): ProvideHttpLoggingInterceptor {

        override fun httpLoggingInterceptor() =
            HttpLoggingInterceptor().setLevel(level = logLevel)
    }

    private object Debug: Abstract(HttpLoggingInterceptor.Level.BODY)

    private object Release: Abstract(HttpLoggingInterceptor.Level.NONE)

    class Factory(debuggable: Boolean): ProvideHttpLoggingInterceptor {

        private val interceptor = if(debuggable) Debug else Release

        override fun httpLoggingInterceptor() = interceptor.httpLoggingInterceptor()
    }
}