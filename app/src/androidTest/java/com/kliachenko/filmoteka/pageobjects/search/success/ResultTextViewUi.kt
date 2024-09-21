package com.kliachenko.filmoteka.pageobjects.search.success

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

class ResultTextViewUi(parentId: Int, parentClass: Matcher<View>) {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val resultTextColor =
        ContextCompat.getColor(uiContext, com.kliachenko.core.R.color.white)
    private val resultTextViewId: Matcher<View> = withId(com.kliachenko.search.R.id.subscriptionTextView)
    private val interaction: ViewInteraction = onView(
        allOf(
            withParent(withId(parentId)), parentClass,
            resultTextViewId,
            isAssignableFrom(MaterialTextView::class.java)
        )
    )

    fun checkSuccessState(subscriptionText: String) {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(ColorMatcher(resultTextColor)))
            check(matches(TextMatcher(subscriptionText)))
        }
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
