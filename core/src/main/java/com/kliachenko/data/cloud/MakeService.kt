package com.kliachenko.data.cloud

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface MakeService {

    fun <T : Any> create(clazz: Class<T>): T

    class Base(provideInterceptor: ProvideHttpLoggingInterceptor) : MakeService {

        private val okHttpClient = OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(provideInterceptor.httpLoggingInterceptor())
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        override fun <T : Any> create(clazz: Class<T>): T = retrofit.create(clazz)

        companion object {
            private const val BASE_URL = "https://api.themoviedb.org/"
        }

    }
}