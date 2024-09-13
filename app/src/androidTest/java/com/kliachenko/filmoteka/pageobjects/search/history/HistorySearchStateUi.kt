package com.kliachenko.filmoteka.pageobjects.search.history

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.filmoteka.core.RecyclerViewMatcher
import com.kliachenko.filmoteka.pageobjects.search.FilmSearchItem
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class HistorySearchStateUi(
    parentId: Matcher<View>,
    parentClass: Matcher<View>,
    rootId: Int,
    rootClass: Class<RecyclerView>,
) {
    private val searchHistoryStateLayoutId: Int =
        com.kliachenko.search.R.id.searchHistoryStateLayoutId
    private val searchHistoryStateLayoutClass = withParent(isAssignableFrom(LinearLayout::class.java))
    private val recentTextViewUi =
        RecentTextViewUi(parentId = searchHistoryStateLayoutId, parentClass = searchHistoryStateLayoutClass)
    private val searchHistoryUi =
        SearchHistoryUi(parentId = searchHistoryStateLayoutId, parentClass = searchHistoryStateLayoutClass)

    private val rootInteraction = onView(
        allOf(
            parentId,
            parentClass,
            withId(rootId),
            isAssignableFrom(rootClass),
            RecyclerViewMatcher(
                position = 0,
                targetViewId = searchHistoryStateLayoutId,
                recyclerViewId = rootId
            ),
        )
    )

    fun checkVisible(films: List<FilmSearchItem>) {
        rootInteraction.check(matches(isDisplayed()))
        recentTextViewUi.checkSearchHistoryState()
        searchHistoryUi.checkSearchHistoryState(films)
    }

    fun tapFilm(position: Int) {
        searchHistoryUi.tapFilm(position)
    }

    fun isNotVisible() {
        rootInteraction.check(matches(not(isDisplayed())))
        searchHistoryUi.isNotVisible()
        recentTextViewUi.isNotVisible()
    }

}
