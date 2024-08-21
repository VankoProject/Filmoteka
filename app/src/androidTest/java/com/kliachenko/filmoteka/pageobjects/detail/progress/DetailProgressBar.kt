package com.kliachenko.filmoteka.pageobjects.detail.progress

import android.view.View
import android.widget.ProgressBar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.kliachenko.filmoteka.core.waitUntilProgressIsNotDisplayed
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class DetailProgressBar(parentProgressId: Matcher<View>, parentProgressClass: Matcher<View>) {

    private val progressBarId: Int = com.kliachenko.detail.R.id.detailProgressBar
    private val interaction: ViewInteraction = onView(
        allOf(
            parentProgressId,
            parentProgressClass,
            isAssignableFrom(ProgressBar::class.java),
            withId(progressBarId)
        )
    )

    fun waitUntilIsNotVisible() {
        onView(isRoot()).perform(waitUntilProgressIsNotDisplayed(progressBarId, 3000))
    }

    fun checkVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
