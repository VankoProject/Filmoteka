package com.kliachenko.filmoteka.pageobjects.search

import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.search.SearchView
import com.kliachenko.filmoteka.core.SearchViewHintTextColorMatcher
import com.kliachenko.filmoteka.core.SearchViewHintTextMatcher
import com.kliachenko.filmoteka.core.SearchViewInputTextColorMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class SearchViewUi(parentId: Matcher<View>, parentClass: Matcher<View>) {

    private val searchViewId: Int = com.kliachenko.search.R.id.searchViewId
    private val interaction: ViewInteraction = onView(
        allOf(parentId, parentClass, withId(searchViewId), isAssignableFrom(SearchView::class.java))
    )
    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val inputTextColor =
        ContextCompat.getColor(uiContext, com.kliachenko.core.R.color.white)
    private val hintTextColor =
        ContextCompat.getColor(uiContext, com.kliachenko.core.R.color.darkGrey)
    private val searchHintText =
        uiContext.getText(com.kliachenko.detail.R.string.search_hint).toString()

    fun checkInitial() {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(SearchViewHintTextMatcher(searchHintText)))
            check(matches(SearchViewHintTextColorMatcher(hintTextColor)))
        }
    }

    fun type(text: String) {
        onView(
            allOf(
                withParent(withId(searchViewId)),
                withParent(isAssignableFrom(SearchView::class.java)),
                isAssignableFrom(EditText::class.java)
            )
        ).perform(replaceText(text))
        interaction.check(matches(SearchViewInputTextColorMatcher(inputTextColor)))
    }

    fun tapClearAllInput() {
        onView(withId(androidx.appcompat.R.id.search_close_btn)).perform(click())
    }

}
