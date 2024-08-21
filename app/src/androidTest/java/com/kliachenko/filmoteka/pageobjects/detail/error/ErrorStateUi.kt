package com.kliachenko.filmoteka.pageobjects.detail.error

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.filmoteka.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class ErrorStateUi(parentId: Matcher<View>, parentClass: Matcher<View>) {

    private val parentErrorId: Matcher<View> = withParent(withId(R.id.errorStateLayout))
    private val parentErrorClass: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))
    private val rootInteraction: ViewInteraction = onView(allOf(parentId, parentClass))
    private val errorTextView = ErrorDetailTextView(parentErrorId, parentErrorClass)
    private val retryButton = RetryDetailButton(parentErrorId, parentErrorClass)

    fun checkVisible(message: String) {
        rootInteraction.check(matches(isDisplayed()))
        errorTextView.checkVisible(message)
        retryButton.checkVisible()
    }

    fun tap() {
        retryButton.tap()
    }

    fun isNotVisible() {
        rootInteraction.check(matches(not(isDisplayed())))
        errorTextView.isNotVisible()
        retryButton.isNotVisible()
    }

}
