package com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.score

import android.view.View
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.filmoteka.core.ColorMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class ScoreTextUi(statisticsId: Matcher<View>, statisticsRootLayout: Matcher<View>) {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val textColor = ContextCompat.getColor(uiContext, com.kliachenko.core.R.color.white)
    private val scoreText = uiContext.getText(com.kliachenko.detail.R.string.tmdb).toString()
    private val scoreTextId: Int = com.kliachenko.detail.R.id.scoreTextViewId
    private val interaction: ViewInteraction = onView(
        allOf(
            statisticsId, statisticsRootLayout,
            withId(scoreTextId),
            isAssignableFrom(MaterialTextView::class.java)
        )
    )

    fun checkSuccess() {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(withText(scoreText)))
            check(matches(ColorMatcher(textColor)))
        }

    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
