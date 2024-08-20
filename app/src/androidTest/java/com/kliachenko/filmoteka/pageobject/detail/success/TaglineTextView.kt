package com.kliachenko.filmoteka.pageobject.detail.success

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.core.ColorMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class TaglineTextView(parentSuccessId: Matcher<View>, parentSuccessClass: Matcher<View>) {

    private val taglineId: Int = R.id.taglineTextView
    private val textColor = "#B7FF7E"
    private val interaction: ViewInteraction = onView(
        allOf(
            parentSuccessId, parentSuccessClass,
            isAssignableFrom(MaterialTextView::class.java),
            withId(taglineId)
        )
    )

    fun checkSuccess(filmName: String) {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(withText(filmName)))
            check(matches(ColorMatcher(textColor)))
        }
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
