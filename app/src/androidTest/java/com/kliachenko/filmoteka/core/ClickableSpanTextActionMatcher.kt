package com.kliachenko.filmoteka.core

import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.View
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.google.android.material.textview.MaterialTextView
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class ClickableSpanTextActionMatcher(
    private val text: String,
) : ViewAction {

    override fun getConstraints(): Matcher<View> {
        return allOf(isDisplayed(), isAssignableFrom(MaterialTextView::class.java))
    }

    override fun getDescription(): String {
        return "click on ClickableSpan in text: $text"
    }

    override fun perform(uiController: UiController?, view: View?) {
        val textView = view as? MaterialTextView ?: throw NoMatchingViewException.Builder()
            .withRootView(view)
            .build()
        val spannableText = textView.text as? SpannableString
            ?: throw AssertionError("TextView text is not a SpannableString")

        if (spannableText.isEmpty())
            throw AssertionError("SpannableString is empty")

        val spans = spannableText.getSpans(0, spannableText.length, ClickableSpan::class.java)
        val clickableSpan = spans.firstOrNull {spannableText.getSpanStart(it) == spannableText.indexOf(text)} ?:
        throw AssertionError("No ClickableSpan found for text: $text")

        clickableSpan.onClick(textView)
    }
}