package com.kliachenko.filmoteka.pageobjects.detail.success

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

class CountryProductionTextView(parentSuccessId: Matcher<View>, parentSuccessClass: Matcher<View>) {

    private val countryProductionTextViewId: Int = R.id.countryProductionTextView
    private val category = "Country production"
    private val textColor = "#FFFFFF"
    private val interaction: ViewInteraction = onView(
        allOf(
            parentSuccessId, parentSuccessClass,
            isAssignableFrom(MaterialTextView::class.java),
            withId(countryProductionTextViewId)
        )
    )

    fun checkSuccess(countryProduction: String) {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(ColorMatcher(textColor)))
            check(matches(CombineTextMatcher(category, countryProduction)))
        }
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
