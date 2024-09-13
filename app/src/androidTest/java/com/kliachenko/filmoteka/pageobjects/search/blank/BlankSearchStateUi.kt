package com.kliachenko.filmoteka.pageobjects.search.blank

import android.view.View
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

class BlankSearchStateUi(
    parentId: Matcher<View>,
    parentClass: Matcher<View>,
    rootId: Int,
    rootClass: Class<RecyclerView>,
) {

    private val blankSearchTextViewId: Int = com.kliachenko.search.R.id.blankSearchTextViewId
    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val textColor = ContextCompat.getColor(uiContext, com.kliachenko.core.R.color.white)
    private val initialText =
        uiContext.getText(com.kliachenko.search.R.string.blank_search_message).toString()

    private val rootInteraction = onView(
        allOf(
            parentId, parentClass,
            withId(rootId),
            RecyclerViewMatcher(
                position = 0,
                targetViewId = blankSearchTextViewId,
                recyclerViewId = rootId
            ),
        )
    )

    private val textInteraction = onView(
        allOf(
            withParent(withId(rootId)),
            withParent(isAssignableFrom(rootClass)),
            withId(blankSearchTextViewId),
            isAssignableFrom(MaterialTextView::class.java)
        )
    )

    fun checkVisible() {
        rootInteraction.check(matches(isDisplayed()))
        textInteraction.apply {
            check(matches(isDisplayed()))
            check(matches(ColorMatcher(textColor)))
            check(matches(TextMatcher(initialText)))
        }
    }

    fun isNotVisible() {
        textInteraction.check(matches(not(isDisplayed())))
    }

}
