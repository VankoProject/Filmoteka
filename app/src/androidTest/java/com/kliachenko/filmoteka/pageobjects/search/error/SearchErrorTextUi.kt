package com.kliachenko.filmoteka.pageobjects.search.error

import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.filmoteka.core.ColorMatcher
import com.kliachenko.filmoteka.core.RecyclerViewMatcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class SearchErrorTextUi(rootId: Int, rootClass: Class<RecyclerView>) {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val searchErrorStateLayoutId: Int =
        com.kliachenko.search.R.id.searchErrorStateLayoutId
    private val searchErrorTextId: Int = com.kliachenko.search.R.id.searchErrorTextViewId
    private val errorTextColor =
        ContextCompat.getColor(uiContext, com.kliachenko.core.R.color.error)

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

    private val textInteraction = onView(
        allOf(
            withParent(withId(searchErrorStateLayoutId)),
            withParent(isAssignableFrom(LinearLayout::class.java)),
            withId(searchErrorTextId),
            isAssignableFrom(MaterialTextView::class.java)
        )
    )

    fun checkErrorState(errorMessage: String) {
        rootInteraction.check(matches(isDisplayed()))
        textInteraction.apply {
            check(matches(isDisplayed()))
            check(matches(ColorMatcher(errorTextColor)))
            check(matches(withText(errorMessage)))
        }
    }

    fun isNotVisible() {
        rootInteraction.check(matches(not(isDisplayed())))
        textInteraction.check(matches(not(isDisplayed())))
    }

}
