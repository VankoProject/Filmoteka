package com.kliachenko.filmoteka.core

import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.textview.MaterialTextView
import org.hamcrest.Description

class ClickableSpanTextMatcher(
    private val text: String,
) : BoundedMatcher<View, MaterialTextView>(MaterialTextView::class.java) {
    override fun describeTo(description: Description?) {
        description!!.appendText("has ClickableSpan for text: $text")
    }

    override fun matchesSafely(item: MaterialTextView?): Boolean {
        val spannable = item?.text as? SpannableString ?: return false
        val spans = spannable.getSpans(0, spannable.length, ClickableSpan::class.java)
        return spans.any { spannable.getSpanStart(it) == spannable.indexOf(text) }
    }
}