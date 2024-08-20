package com.kliachenko.filmoteka.pageobject.detail.success

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf

object SnackBarUi {

    private const val textForAdded = "Added to favorites"
    private const val textForRemoved = "Removed from favorites"

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
