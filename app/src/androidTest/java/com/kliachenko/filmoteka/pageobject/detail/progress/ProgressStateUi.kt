package com.kliachenko.filmoteka.pageobject.detail.progress

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

class ProgressStateUi(parentId: Matcher<View>, parentClass: Matcher<View>) {

    private val parentProgressId: Matcher<View> = withParent(withId(R.id.progressDetailLayout))
    private val parentProgressClass: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))
    private val rootInteraction: ViewInteraction = onView(allOf(parentId, parentClass))
    private val progressBar = DetailProgressBar(parentProgressId, parentProgressClass)
    private val progressTextView = DetailProgressTextView(parentProgressId, parentProgressClass)

    fun waitUntilIsNotVisible() {
        progressBar.waitUntilIsNotVisible()
    }

    fun isNotVisible() {
        rootInteraction.check(matches(not(isDisplayed())))
        progressBar.isNotVisible()
        progressTextView.isNotVisible()
    }

    fun checkVisible(message: String) {
        rootInteraction.check(matches(isDisplayed()))
        progressBar.checkVisible()
        progressTextView.checkVisible(message)
    }

}
