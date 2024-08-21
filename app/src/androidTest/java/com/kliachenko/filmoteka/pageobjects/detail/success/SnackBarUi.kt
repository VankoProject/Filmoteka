package com.kliachenko.filmoteka.pageobjects.detail.success

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matchers.allOf

object SnackBarUi {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val textForAdded =
        uiContext.getText(com.kliachenko.detail.R.string.added_to_favorites).toString()
    private val textForRemoved =
        uiContext.getText(com.kliachenko.detail.R.string.removed_from_favorites).toString()

    fun checkSnackBarDisplayedWithText(expectedSelected: Boolean) {
        val expectedText = if (expectedSelected)
            textForAdded
        else
            textForRemoved

        onView(
            allOf(
                withId(com.google.android.material.R.id.snackbar_text),
                withText(expectedText),
            )
        ).check(matches(isDisplayed()))
    }
}
