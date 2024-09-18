package com.kliachenko.filmoteka.pageobjects.search.success

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

class SuccessSearchStateUi(
    parentId: Matcher<View>,
    parentClass: Matcher<View>,
    rootId: Int,
    rootClass: Class<RecyclerView>,
) {

    private val searchSuccessStateLayoutId =
        com.kliachenko.search.R.id.searchSuccessStateLayout
    private val searchSuccessStateLayoutClass = withParent(isAssignableFrom(LinearLayout::class.java))
    private val resultTextViewUi =
        ResultTextViewUi(parentId = searchSuccessStateLayoutId, parentClass = searchSuccessStateLayoutClass)
    private val searchResultsUi =
        SearchResultsUi(parentId = searchSuccessStateLayoutId, parentClass = searchSuccessStateLayoutClass)

    private val rootInteraction = onView(
        allOf(
            parentId,
            parentClass,
            withId(rootId),
            isAssignableFrom(rootClass),
            RecyclerViewMatcher(
                position = 0,
                targetViewId = searchSuccessStateLayoutId,
                recyclerViewId = rootId
            ),
        )
    )

    fun checkVisible(subscriptionText: String, films: List<FilmSearchItem>) {
        rootInteraction.check(matches(isDisplayed()))
        resultTextViewUi.checkSuccessState(subscriptionText = subscriptionText)
        searchResultsUi.checkSuccessState(films = films)
    }

    fun tapFilm(position: Int) {
        searchResultsUi.tapFilm(position)
    }


    fun isNotVisible() {
        rootInteraction.check(matches(not(isDisplayed())))
        resultTextViewUi.isNotVisible()
        searchResultsUi.isNotVisible()
    }

}
