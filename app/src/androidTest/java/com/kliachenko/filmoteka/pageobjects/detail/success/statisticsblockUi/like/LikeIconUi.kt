package com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.like

import android.view.View
import android.widget.ImageButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class LikeIconUi(statisticsId: Matcher<View>, statisticsRootLayout: Matcher<View>) {

    private val likeIconId: Int = com.kliachenko.detail.R.id.likeIconId
    private val interaction: ViewInteraction = onView(
        allOf(
            statisticsId, statisticsRootLayout,
            withId(likeIconId),
            isAssignableFrom(ImageButton::class.java)
        )
    )

    fun checkSuccess() {
        interaction.check(matches(isDisplayed()))
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun tap() {
        interaction.perform(click())
    }

}
