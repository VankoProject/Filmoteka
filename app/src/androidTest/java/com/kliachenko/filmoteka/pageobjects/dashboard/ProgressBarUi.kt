package com.kliachenko.filmoteka.pageobjects.dashboard

import android.view.View
import android.widget.ProgressBar
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import com.kliachenko.dashboard.R
import com.kliachenko.filmoteka.core.RecyclerViewMatcher
import com.kliachenko.filmoteka.core.waitUntilProgressIsNotDisplayed
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

/**
Properties:
1. visibility
 */

class ProgressBarUi(parentId: Matcher<View>, parentClass: Matcher<View>) {

    @IdRes
    private val progressBarId: Int = R.id.progressBar

    private val progressBarMatcher = RecyclerViewMatcher(
        position = 0,
        targetViewId = progressBarId,
        recyclerViewId = R.id.dashboardRecyclerView
    )

    private val interaction: ViewInteraction = onView(
        allOf(
            parentId,
            parentClass,
            progressBarMatcher,
            isAssignableFrom(ProgressBar::class.java)
        )
    )

    fun checkDashboardProgressState() {
        interaction.check(matches((isDisplayed())))
    }

    fun waitUntilIsNotVisible() {
        onView(isRoot()).perform(waitUntilProgressIsNotDisplayed(progressBarId, 3000))
    }

    fun checkErrorState() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun checkSuccessfulState() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun checkDashboardIsNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
