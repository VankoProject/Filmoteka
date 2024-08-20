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

class OverViewTextView(parentSuccessId: Matcher<View>, parentSuccessClass: Matcher<View>) {

    private val overviewFilmId: Int = R.id.overviewFilmId
    private val textColor = "#FFFFFF"
    private val interaction: ViewInteraction = onView(
        allOf(
            parentSuccessId, parentSuccessClass,
            isAssignableFrom(MaterialTextView::class.java),
            withId(overviewFilmId)
        )
    )

    fun checkSuccess(overView: String) {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(withText(overView)))
            check(matches(ColorMatcher(textColor)))
        }
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
