package com.kliachenko.core

import android.content.Context

interface ConvertStringUiDetailItems {

    fun statistics(
        adult: Boolean,
        genres: List<String>,
        releaseDate: String,
        runtime: Int,
    ): String

    fun countryProduction(
        countryProduction: List<String>,
    ): String

    fun originalLanguage(
        originalLanguage: String,
    ): String

    fun homePage(
        homePage: String,
    ): String

    class Base(
        private val context: Context,
    ) : ConvertStringUiDetailItems {
        override fun statistics(
            adult: Boolean,
            genres: List<String>,
            releaseDate: String,
            runtime: Int,
        ): String {
            val genre = genres.joinToString(separator = ", ") { it.uppercase() }.takeIf {
                it.isNotEmpty()
            } ?: ""
            val age =
                if (adult) context.getString(R.string.adult_age) else context.getString(R.string.all_ages)
            val time = "$runtime ${context.getString(R.string.minutes_label)}"
            return "$genre | $releaseDate | $time | $age"
        }

        override fun countryProduction(countryProduction: List<String>): String {
            val startString = context.getString(R.string.country_production)
            val countries = countryProduction.joinToString(separator = ", ")
            return "$startString: $countries"
        }

        override fun originalLanguage(originalLanguage: String): String {
            val startString = context.getString(R.string.original_language)
            return "$startString: $originalLanguage"
        }

        override fun homePage(homePage: String): String {
            val startString = context.getString(R.string.home_page)
            return "$startString: $homePage"
        }
    }

}