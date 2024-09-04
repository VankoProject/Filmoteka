package com.kliachenko.detail.presentation.customViews

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.detail.R

class CustomDetailUiTextView : MaterialTextView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    fun characteristics(
        adult: Boolean,
        genres: List<String>,
        releaseDate: String,
        runtime: Int,
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
        val result = "$startString: $originalLanguage"
        text = result
    }

    fun homePage(homePage: String) {
        val startString = context.getString(R.string.home_page)
        val linkHolder = context.getString(R.string.hyper_link)
        val result = "$startString: $linkHolder"

        val spannableString = SpannableString(result)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(homePage))
                context.startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = ContextCompat.getColor(context, com.kliachenko.core.R.color.royalBlue)
            }
        }

        val startIndex = result.indexOf(linkHolder)
        val endIndex = startIndex + linkHolder.length
        spannableString.setSpan(
            clickableSpan,
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        text = spannableString
        Log.d("Filmoteka", "home page $text")
        movementMethod = LinkMovementMethod.getInstance()
    }

    fun likesCount(voteCount: Int) {
        val startString = context.getString(R.string.like_count)
        val result = "$startString: $voteCount"
        text = result
    }
}