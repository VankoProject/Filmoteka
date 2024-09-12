package com.kliachenko.filmoteka.pageobjects.search

import android.view.View
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.filmoteka.core.ColorMatcher
import com.kliachenko.filmoteka.core.TextMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class InitialSearchStateUi(parentId: Matcher<View>, parentClass: Matcher<View>) {

    private val rootId: Int = com.kliachenko.search.R.id.rootInitialSearchStateId
    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val textColor = ContextCompat.getColor(uiContext, com.kliachenko.core.R.color.white)
    private val initialText =
        uiContext.getText(com.kliachenko.search.R.string.initial_search_message).toString()
    private val interaction = onView(
        allOf(
            parentId, parentClass, withId(rootId), isAssignableFrom(MaterialTextView::class.java)
        )
    )

    fun checkVisible() {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(ColorMatcher(textColor)))
            check(matches(TextMatcher(initialText)))
        }
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
