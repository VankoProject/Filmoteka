package com.kliachenko.filmoteka.pageobjects.search.history

import android.view.View
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.filmoteka.core.ColorMatcher
import com.kliachenko.filmoteka.core.TextMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class RecentTextViewUi(parentId: Int, parentClass: Matcher<View>?) {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val recentText =
        uiContext.getText(com.kliachenko.search.R.string.recent).toString()
    private val recentTextColor =
        ContextCompat.getColor(uiContext, com.kliachenko.core.R.color.white)
    private val recentTextViewId: Matcher<View> = withId(com.kliachenko.search.R.id.recentTextView)

    private val interaction: ViewInteraction = onView(
        allOf(
            withParent(withId(parentId)), parentClass,
            recentTextViewId,
            isAssignableFrom(MaterialTextView::class.java)
        )
    )

    fun checkSearchHistoryState() {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(ColorMatcher(recentTextColor)))
            check(matches(TextMatcher(recentText)))
        }
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
