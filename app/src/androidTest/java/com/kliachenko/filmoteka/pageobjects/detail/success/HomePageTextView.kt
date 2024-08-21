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
import com.kliachenko.filmoteka.core.ClickableSpanTextActionMatcher
import com.kliachenko.filmoteka.core.ClickableSpanTextMatcher
import com.kliachenko.filmoteka.core.CombineTextMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class HomePageTextView(parentSuccessId: Matcher<View>, parentSuccessClass: Matcher<View>) {

    private val homePageTextId: Int = R.id.homePageTextId
    private val textPlug = "Visit Official Website"
    private val category = "Home page"
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
            check(matches(CombineTextMatcher(category, textPlug)))
            check(matches(ClickableSpanTextMatcher(textPlug)))
        }
    }

    fun click() {
        interaction.perform(ClickableSpanTextActionMatcher(textPlug))
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
