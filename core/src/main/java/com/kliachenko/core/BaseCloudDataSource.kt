package com.kliachenko.core

import retrofit2.Call

abstract class BaseCloudDataSource {

    protected fun <T : Any> handleRequest(request: Call<T>): T =
        request.execute().body()!!

}