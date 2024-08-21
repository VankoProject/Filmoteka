package com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.like

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.core.ColorMatcher
import com.kliachenko.filmoteka.core.CombineTextMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class LikeCountTextUi(statisticsId: Matcher<View>, statisticsRootLayout: Matcher<View>) {

    private val plugText = "Like"
    private val textColor = "#FFFFFF"
    private val likeCountTextId: Int = R.id.likeCountTextViewId
    private val interaction: ViewInteraction = onView(
        allOf(
            statisticsId, statisticsRootLayout,
            withId(likeCountTextId),
            isAssignableFrom(MaterialTextView::class.java)
        )
    )

    fun checkSuccess(likeCount: Int) {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(CombineTextMatcher(plugText, likeCount.toString())))
            check(matches(ColorMatcher(textColor)))
        }

    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
