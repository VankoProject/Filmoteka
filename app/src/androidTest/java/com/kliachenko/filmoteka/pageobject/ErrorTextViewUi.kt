package com.kliachenko.filmoteka.pageobject

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.matchers.ColorMatcher
import com.kliachenko.filmoteka.matchers.RecyclerViewMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

/**
Properties:
1. color //1
2. visibility
 */

class ErrorTextViewUi(parentId: Matcher<View>, parentClass: Matcher<View>) {

    private val errorTextViewMatcher = RecyclerViewMatcher(
        position = 0,
        targetViewId = R.id.errorTextView,
        recyclerViewId = R.id.dashboardRecyclerView
    )

    private val interaction: ViewInteraction = onView(
        allOf(
            parentId,
            parentClass,
            errorTextViewMatcher,
            isAssignableFrom(TextView::class.java)
        )
    )

    fun checkDashboardProgressState() {
        interaction.apply {
            check(matches(not(isDisplayed())))
            check(matches(ColorMatcher("FF0505")))
        }
    }

    fun checkErrorState(message: String) {
        interaction.check(matches(withText(message)))
    }

    fun checkSuccessfulState() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun checkDashboardIsNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }


}
