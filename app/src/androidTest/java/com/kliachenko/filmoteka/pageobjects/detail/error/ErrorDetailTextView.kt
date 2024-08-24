package com.kliachenko.filmoteka.pageobjects.detail.error

import android.view.View
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
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

class ErrorDetailTextView(parentId: Matcher<View>, parentClass: Matcher<View>) {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val errorTextColor = ContextCompat.getColor(uiContext,com.kliachenko.core.R.color.error )
    private val errorDetailTextViewId: Int = com.kliachenko.detail.R.id.errorDetailTextView
    private val interaction: ViewInteraction = onView(
        allOf(
            parentId,
            parentClass,
            isAssignableFrom(MaterialTextView::class.java),
            withId(errorDetailTextViewId)
        )
    )

    fun checkVisible(message: String) {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(TextMatcher(message)))
            check(matches(ColorMatcher(errorTextColor)))
        }
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }
}
