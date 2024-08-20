package com.kliachenko.filmoteka.pageobject.detail.success

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.filmoteka.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class HomePageTextView(parentSuccessId: Matcher<View>, parentSuccessClass: Matcher<View>) {

    private val homePageTextId: Int = R.id.homePageTextId
    private val textPlug = "Visit Official Website"
    private val interaction: ViewInteraction = onView(
        allOf(
            parentSuccessId, parentSuccessClass,
            withId(homePageTextId),
            isAssignableFrom(MaterialTextView::class.java)
        )
    )

    fun checkSuccess() {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(withText(textPlug)))
            check(matches(isClickable()))
        }
    }

    fun click(homePage: String) {
        interaction.perform(click())
    }

}
