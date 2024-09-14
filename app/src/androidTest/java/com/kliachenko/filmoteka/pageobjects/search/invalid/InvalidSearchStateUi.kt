package com.kliachenko.filmoteka.pageobjects.search.invalid

import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.filmoteka.core.ColorMatcher
import com.kliachenko.filmoteka.core.RecyclerViewMatcher
import com.kliachenko.filmoteka.core.TextMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class InvalidSearchStateUi(
    parentId: Matcher<View>,
    parentClass: Matcher<View>,
    rootId: Int,
    rootClass: Class<RecyclerView>,
) {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val invalidText =
        uiContext.getText(com.kliachenko.search.R.string.continue_search_text).toString()
    private val invalidTextColor =
        ContextCompat.getColor(uiContext, com.kliachenko.core.R.color.white)
    private val searchInvalidStateLayoutId: Int =
        com.kliachenko.search.R.id.searchInvalidStateLayoutId
    private val invalidTextViewId: Int = com.kliachenko.search.R.id.invalidTextViewId
    private val invalidProgressbarId: Int = com.kliachenko.search.R.id.invalidProgressbarId

    private val rootInteraction = onView(
        allOf(
            parentId,
            parentClass,
            withId(rootId),
            isAssignableFrom(rootClass),
            RecyclerViewMatcher(
                position = 0,
                targetViewId = searchInvalidStateLayoutId,
                recyclerViewId = rootId
            ),
        )
    )

    private val textInteraction = onView(
        allOf(
            withParent(withId(searchInvalidStateLayoutId)),
            withParent(isAssignableFrom(LinearLayout::class.java)),
            withId(invalidTextViewId),
            isAssignableFrom(MaterialTextView::class.java)
        )
    )

    private val progressInteraction = onView(
        allOf(
            withParent(withId(searchInvalidStateLayoutId)),
            withParent(isAssignableFrom(LinearLayout::class.java)),
            withId(invalidProgressbarId),
            isAssignableFrom(ProgressBar::class.java)
        )
    )

    fun checkVisible() {
        rootInteraction.check(matches(isDisplayed()))
        progressInteraction.check(matches(isDisplayed()))
        textInteraction.apply {
            check(matches(isDisplayed()))
            check(matches(TextMatcher(invalidText)))
            check(matches(ColorMatcher(invalidTextColor)))
        }
    }

    fun isNotVisible() {
        rootInteraction.check(matches(not(isDisplayed())))
    }

}
