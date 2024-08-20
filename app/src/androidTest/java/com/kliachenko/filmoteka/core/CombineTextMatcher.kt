package com.kliachenko.filmoteka.core

import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.textview.MaterialTextView
import org.hamcrest.Description

class CombineTextMatcher(
    private val genres: List<String>,
    private val releaseDate: String,
    private val runtime: String,
    private val adult: Boolean,
) : BoundedMatcher<View, TextView>(MaterialTextView::class.java) {

    override fun describeTo(description: Description) {
        description.appendText(
            "with combined text matching the format:" +
                    " 'GENRES | RELEASE_DATE | RUNTIME min | AGE_RATING'"
        )
    }

    override fun matchesSafely(item: TextView): Boolean {
        val expectedText = genres.joinToString(", ")
            .plus(" | $releaseDate | $runtime min | ${if (adult) "18+" else "All ages"}")
        return item.text.toString() == expectedText
    }
}