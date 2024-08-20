package com.kliachenko.filmoteka.pageobject.detail.success

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.core.ColorMatcher
import com.kliachenko.filmoteka.core.CombineTextMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class OriginalLanguageTextView(parentSuccessId: Matcher<View>, parentSuccessClass: Matcher<View>) {

    private val originalLanguageId: Int = R.id.originalLanguageTextView
    private val category = "Original language"
    private val textColor = "#FFFFFF"
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
