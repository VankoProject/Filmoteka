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
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.core.ColorMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class ScoreIconText(scoreUiId: Matcher<View>, scoreLayout: Matcher<View>) {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val textColor = ContextCompat.getColor(uiContext, R.color.white)
    private val scoreIconTextId: Int = R.id.scoreIconTextId
    private val interaction: ViewInteraction = onView(
        allOf(
            scoreUiId, scoreLayout,
            withId(scoreIconTextId),
            isAssignableFrom(MaterialTextView::class.java)
        )
    )

    fun checkSuccess(score: String) {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(withText(score)))
            check(matches(ColorMatcher(textColor)))
        }

    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
