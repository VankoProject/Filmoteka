package com.kliachenko.filmoteka.pageobjects.search.error

import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.platform.app.InstrumentationRegistry
import com.kliachenko.filmoteka.core.RecyclerViewMatcher
import com.kliachenko.filmoteka.core.TextMatcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class SearchRetryButtonUi(rootId: Int, rootClass: Class<RecyclerView>) {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val searchErrorStateLayoutId: Int =
        com.kliachenko.search.R.id.searchErrorStateLayoutId
    private val searchRetryButtonId: Int = com.kliachenko.search.R.id.searchRetryButtonId
    private val errorTextRetryButton =
        uiContext.getText(com.kliachenko.core.R.string.retry_button).toString()

    private val rootInteraction: ViewInteraction = onView(
        allOf(
            withParent(isAssignableFrom(rootClass)),
            withParent(withId(rootId)),
            RecyclerViewMatcher(
                position = 0,
                targetViewId = searchErrorStateLayoutId,
                recyclerViewId = rootId
            )
        )
    )

    private val buttonInteraction = onView(
        allOf(
            withParent(withId(searchErrorStateLayoutId)),
            withParent(isAssignableFrom(LinearLayout::class.java)),
            withId(searchRetryButtonId),
            isAssignableFrom(AppCompatButton::class.java),
        )
    )

    fun checkErrorState() {
        rootInteraction.check(matches(isDisplayed()))
        buttonInteraction.check(matches(isDisplayed()))
            .check(matches(TextMatcher(errorTextRetryButton)))
    }

    fun tap() {
        buttonInteraction.perform(click())
    }

    fun isNotVisible() {
        rootInteraction.check(matches(not(isDisplayed())))
    }

}
