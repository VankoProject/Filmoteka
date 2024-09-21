package com.kliachenko.filmoteka.pageobjects.search.error

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.kliachenko.filmoteka.core.RecyclerViewMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class ErrorSearchStateUi(
    parentId: Matcher<View>,
    parentClass: Matcher<View>,
    rootId: Int,
    rootClass: Class<RecyclerView>,
) {

    private val errorStateLayoutId = com.kliachenko.search.R.id.searchErrorStateLayout
    private val searchRetryButtonUi = SearchRetryButtonUi(rootId, rootClass)
    private val searchErrorTextUi = SearchErrorTextUi(rootId, rootClass)

    private val rootInteraction = onView(
        allOf(
            parentId,
            parentClass,
            withId(rootId),
            isAssignableFrom(rootClass),
            RecyclerViewMatcher(
                position = 0,
                targetViewId = errorStateLayoutId,
                recyclerViewId = rootId
            ),
        )
    )

    fun checkVisible(errorMessage: String) {
        rootInteraction.check(matches(isDisplayed()))
        searchErrorTextUi.checkErrorState(errorMessage = errorMessage)
        searchRetryButtonUi.checkErrorState()
    }

    fun retry() {
        searchRetryButtonUi.tap()
    }

    fun isNotVisible() {
        rootInteraction.check(matches(not(isDisplayed())))
        searchErrorTextUi.isNotVisible()
        searchRetryButtonUi.isNotVisible()
    }

}
