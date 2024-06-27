package com.kliachenko.filmoteka.pageobject.dashboard

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.core.ColorMatcher
import com.kliachenko.filmoteka.core.RecyclerViewMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

/**
Properties:
1. color //1
2. visibility
 */

class ProgressTextViewUi(parentId: Matcher<View>, parentClass: Matcher<View>) {

    private val progressTextViewMatcher = RecyclerViewMatcher(
        position = 1,
        targetViewId = R.id.progressTextView,
        recyclerViewId = R.id.dashboardRecyclerView
    )

    private val interaction: ViewInteraction = onView(
        allOf(
            parentId,
            parentClass,
            progressTextViewMatcher,
            isAssignableFrom(TextView::class.java)
        )
    )

    fun checkDashboardProgressState(message: String) {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(withText(message)))
            check(matches(ColorMatcher("#FFFFFF")))
        }
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
