package com.kliachenko.filmoteka.pageobjects.detail.error

import android.view.View
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.button.MaterialButton
import com.kliachenko.filmoteka.core.ColorMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class RetryDetailButton(parentId: Matcher<View>, parentClass: Matcher<View>) {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val textColor = ContextCompat.getColor(uiContext, com.kliachenko.detail.R.color.white)
    private val buttonText = uiContext.getText(com.kliachenko.detail.R.string.retry_button).toString()
    private val retryDetailButtonId: Int = com.kliachenko.detail.R.id.retryDetailButton
    private val interaction: ViewInteraction = onView(
        allOf(
            parentId,
            parentClass,
            isAssignableFrom(MaterialButton::class.java),
            withId(retryDetailButtonId)
        )
    )

    fun checkVisible() {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(withText(buttonText)))
            check(matches(ColorMatcher(textColor)))
        }
    }

    fun tap() {
        interaction.perform(click())
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
