package com.kliachenko.filmoteka.core

import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.textview.MaterialTextView
import org.hamcrest.Description

class CombineTextMatcher(
    private val firstText: String,
    private val secondText: String
): BoundedMatcher<View, TextView>(MaterialTextView::class.java) {

    override fun describeTo(description: Description) {
        description.appendText(
            "with combined text matching the format: '$firstText: $secondText'"
        )
    }

    override fun matchesSafely(item: TextView): Boolean {
        val expectedText =  "$firstText: $secondText"
        return item.text.toString() == expectedText
    }
}