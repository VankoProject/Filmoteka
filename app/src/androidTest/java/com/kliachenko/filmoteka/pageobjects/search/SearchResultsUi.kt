package com.kliachenko.filmoteka.pageobjects.search

import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.dashboard.R
import com.kliachenko.filmoteka.core.LinearLayoutChildPositionMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class SearchResultsUi(parentId: Matcher<View>, parentClass: Matcher<View>) {

    private val rootLayoutId: Matcher<View> = withId(com.kliachenko.search.R.scrollViewId)
    private val rootLayoutClass: Matcher<View> = isAssignableFrom(ScrollView::class.java)
    private val filmsContainerLayoutId: Matcher<View> =
        withParent(withId(com.kliachenko.search.R.id.filmsContainerLayoutId))
    private val filmsContainerClass: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))
    private val searchFilmItemId: Matcher<View> = withId(R.id.searchfilmItemLayoutId)
    private val searchFilmItemClass: Matcher<View> = isAssignableFrom(LinearLayout::class.java)
    private val rootInteraction = onView(
        allOf(
            parentId, parentClass, rootLayoutId, rootLayoutClass
        )
    )
    private val filmItemInteraction = onView(
        allOf(
            filmsContainerLayoutId,
            filmsContainerClass,
            searchFilmItemId,
            searchFilmItemClass
        )
    )

    fun checkSuccessState(films: List<FilmSearchItem>) {
        rootInteraction.check(matches(isDisplayed()))
        filmItemInteraction.check(matches(isDisplayed()))
        films.forEach {
            it.checkSuccess()
        }
    }

    fun tapFilm(position: Int) {
        onView(
            allOf(
                filmsContainerLayoutId,
                filmsContainerClass,
                LinearLayoutChildPositionMatcher(position, searchFilmItemId)
            )
        ).perform(click())
    }

    fun isNotVisible() {
        rootInteraction.check(matches(not(isDisplayed())))
        filmItemInteraction.check(matches(not(isDisplayed())))
    }

}
