package com.kliachenko.detail.presentation.customViews

import android.annotation.SuppressLint

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.AttributeSet
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

    fun ageRating(adult: Boolean) {
        val startString = context.getString(R.string.age_rating)
        val age =
            if (adult) {
                context.getString(R.string.adult_age)
            } else context.getString(R.string.all_ages)
        val result = "$startString: $age"
        val spannableString = SpannableString(result)
        if (adult) {
            val startIndex = result.indexOf(age)
            val endIndex = startIndex + age.length
            spannableString.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(
                        context,
                        com.kliachenko.core.R.color.error
                    )
                ),
                startIndex,
                endIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString.setSpan(
                StyleSpan(Typeface.BOLD),
                startIndex,
                endIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        text = spannableString
    }

    fun characteristics(
        genres: List<String>,
        releaseDate: String,
        runtime: Int,
    ) {
        val genre = genres.joinToString(separator = ", ") { it.uppercase() }.takeIf {
            it.isNotEmpty()
        } ?: ""
        val time = "$runtime ${context.getString(R.string.minutes_label)}"
        val date = releaseDate.substring(0, 4)
        val result = "$genre | $date | $time"
        text = result
    }

    @SuppressLint("DefaultLocale")
    fun voteAverage(voteAverage: Double) {
        val result = String.format("%.1f", voteAverage)
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
        if(homePage.isNotEmpty()) {
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
                    ds.color = ContextCompat.getColor(context, com.kliachenko.core.R.color.liteLimeGreen)
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
            movementMethod = LinkMovementMethod.getInstance()
        } else visibility = View.GONE

    }

    fun likesCount(voteCount: Int) {
        val startString = context.getString(R.string.like_count)
        val result = "$startString: $voteCount"
        text = result
    }
}