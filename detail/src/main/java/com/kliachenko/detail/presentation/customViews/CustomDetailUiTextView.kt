package com.kliachenko.detail.presentation.customViews

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.core.R

class CustomDetailUiTextView : MaterialTextView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr)

    fun characteristics(
        adult: Boolean,
        genres: List<String>,
        releaseDate: String,
        runtime: Int
    ) {
        val genre = genres.joinToString(separator = ", ") { it.uppercase() }.takeIf {
            it.isNotEmpty()
        } ?: ""
        val age =
            if (adult) context.getString(R.string.adult_age) else context.getString(R.string.all_ages)
        val time = "$runtime ${context.getString(R.string.minutes_label)}"
        val result = "$genre | $releaseDate | $time | $age"
        text = result
    }

    fun countryProduction(countryProduction: List<String>) {
        val startString = context.getString(R.string.country_production)
        val countries = countryProduction.joinToString(separator = ", ")
        val result = "$startString: $countries"
        text = result
    }

    fun originalLanguage(originalLanguage: String) {
        val startString = context.getString(R.string.original_language)
        val result =  "$startString: $originalLanguage"
        text = result
    }

    fun homePage(homePage: String) {
        val startString = context.getString(R.string.home_page)
        val result = "$startString: $homePage"
        text = result
    }
}