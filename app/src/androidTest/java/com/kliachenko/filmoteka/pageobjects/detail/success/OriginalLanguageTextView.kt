package com.kliachenko.filmoteka.pageobjects.detail.success

import android.view.View
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.filmoteka.core.ColorMatcher
import com.kliachenko.filmoteka.core.CombineTextMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class OriginalLanguageTextView(parentSuccessId: Matcher<View>, parentSuccessClass: Matcher<View>) {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val textColor = ContextCompat.getColor(uiContext, com.kliachenko.core.R.color.white)
    private val category = uiContext.getText(com.kliachenko.detail.R.string.original_language).toString()
    private val originalLanguageId: Int = com.kliachenko.detail.R.id.originalLanguageTextView
    private val interaction: ViewInteraction = onView(
        allOf(
            parentSuccessId, parentSuccessClass,
            isAssignableFrom(MaterialTextView::class.java),
            withId(originalLanguageId)
        )
    )

    fun checkSuccess(text: String) {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(ColorMatcher(textColor)))
            check(matches(CombineTextMatcher(category, text)))
        }
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
