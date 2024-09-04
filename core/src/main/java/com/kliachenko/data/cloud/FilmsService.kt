package com.kliachenko.data.cloud

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FilmsService {

    @GET("3/movie/{category}")
    fun filmsByCategory(
        @Path("category") category: String,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LANGUAGE) language: String = "en-US",
        @Query(QUERY_PARAM_PAGE) page: Int
    ): Call<FilmsResponse>

    @GET("3/movie/{movie_id}")
    fun filmDetail(
        @Path("movie_id") filmId: Int,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LANGUAGE) language: String = "en-US"
    ): Call<FilmDetailCloud>

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_LANGUAGE = "language"
        private const val API_KEY = "b728862be4859f4b752498f3ff892837"
    }
}