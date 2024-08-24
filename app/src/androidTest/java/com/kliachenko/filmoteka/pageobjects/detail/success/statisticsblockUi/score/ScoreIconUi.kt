package com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.score

import android.view.View
import android.widget.FrameLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class ScoreIconUi(statisticsId: Matcher<View>, statisticsRootLayout: Matcher<View>) {

    private val scoreUiId: Matcher<View> = withId(com.kliachenko.detail.R.id.scoreIconLayoutId)
    private val scoreLayout: Matcher<View> = isAssignableFrom(FrameLayout::class.java)
    private val interaction: ViewInteraction = onView(
        allOf(
            statisticsId, statisticsRootLayout,
            scoreUiId,
            scoreLayout
        )
    )
    private val scoreIconBackground = ScoreIconBackground(scoreUiId, scoreLayout)
    private val scoreIcon = ScoreIcon(scoreUiId, scoreLayout)
    private val scoreIconText = ScoreIconText(scoreUiId, scoreLayout)

    fun checkSuccess(score: String) {
        interaction.check(matches(isDisplayed()))
        scoreIconBackground.checkSuccess()
        scoreIcon.checkSuccess(score = score)
        scoreIconText.checkSuccess(score = score)
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
        scoreIcon.isNotVisible()
    }

}
