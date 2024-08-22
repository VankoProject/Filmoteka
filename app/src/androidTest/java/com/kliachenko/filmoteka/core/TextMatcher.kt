package com.kliachenko.filmoteka.core

import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.textview.MaterialTextView
import org.hamcrest.Description

class TextMatcher(
    private val text: String,
) : BoundedMatcher<View, MaterialTextView>(MaterialTextView::class.java) {
    override fun describeTo(description: Description) {
        description.appendText("with text: $text")
    }

    override fun matchesSafely(item: MaterialTextView?): Boolean {
        return item?.let {
            item.text.toString() == text
        } ?: throw IllegalStateException("MaterialTextView is null")
    }
}