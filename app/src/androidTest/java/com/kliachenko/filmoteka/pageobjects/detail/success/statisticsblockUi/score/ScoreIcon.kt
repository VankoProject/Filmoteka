package com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.score

import android.view.View
import android.widget.ProgressBar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class ScoreIcon(scoreUiId: Matcher<View>, scoreLayout: Matcher<View>) {

    private val scoreIconId: Int = com.kliachenko.detail.R.id.scoreIconId
    private val interaction: ViewInteraction = onView(
        allOf(
            scoreUiId, scoreLayout,
            withId(scoreIconId),
            isAssignableFrom(ProgressBar::class.java)
        )
    )

    fun checkSuccess(score: String) {
        interaction.check(matches(isDisplayed()))
        interaction.check { view, _ ->
            val progressBar = view as ProgressBar
            val currentProgressFill = progressBar.progress
            if (currentProgressFill != score.toInt()) {
                throw AssertionError("Expected progress: $score but was: $currentProgressFill")
            }
        }
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
