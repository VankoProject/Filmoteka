package com.kliachenko.filmoteka.pageobject.dashboard

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.button.MaterialButton
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.core.ColorMatcher
import com.kliachenko.filmoteka.core.RecyclerViewMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

/**
Properties:
1. color
2. text
3. visibility
 */

class RetryButtonUi(parentId: Matcher<View>, parentClass: Matcher<View>) {

    private val buttonText = "Retry"
    private val buttonBackGround = "#2F479B"
    private val retryButtonMatcher = RecyclerViewMatcher(
        position = 1,
        targetViewId = R.id.retryButton,
        recyclerViewId = R.id.dashboardRecyclerView
    )

    private val interaction: ViewInteraction = onView(
        allOf(
            parentId,
            parentClass,
            retryButtonMatcher,
            isAssignableFrom(MaterialButton::class.java)
        )
    )

    fun checkDashboardProgressState() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun checkErrorState() {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(withText(buttonText)))
            check(matches(ColorMatcher(buttonBackGround)))
        }
    }

    fun tap() {
        interaction.perform(click())
    }

    fun checkSuccessfulState() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun checkDashboardIsNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
