package com.kliachenko.filmoteka.pageobjects.detail.success

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.filmoteka.core.ClickableSpanTextActionMatcher
import com.kliachenko.filmoteka.core.ClickableSpanTextMatcher
import com.kliachenko.filmoteka.core.CombineTextMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class HomePageTextView(parentSuccessId: Matcher<View>, parentSuccessClass: Matcher<View>) {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val category = uiContext.getText(com.kliachenko.detail.R.string.home_page).toString()
    private val textPlug = uiContext.getText(com.kliachenko.detail.R.string.hyper_link).toString()
    private val homePageTextId: Int = com.kliachenko.detail.R.id.homePageTextId
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
