package com.kliachenko.filmoteka.pageobject

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.matchers.DrawableMatcher
import com.kliachenko.filmoteka.matchers.RecyclerViewMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class FilmsListUi(parentId: Matcher<View>, parentClass: Matcher<View>) {

    private val recyclerViewInteraction: ViewInteraction = onView(
        allOf(
            parentId,
            parentClass,
            withId(R.id.dashboardRecyclerView)
        )
    )

    fun checkDashboardProgressState() {

    }

    fun checkErrorState() {

    }

    fun tapBookmarkIcon(position: Int) {
        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.iconImageView,
                recyclerViewId = R.iddashboardRecyclerView
            )
        ).perform(click())
    }

    fun checkSuccessfulState(films: List<Film>) {
        recyclerViewInteraction.check(matches(isDisplayed()))
        films.forEachIndexed { position, film ->
            film.check(position)
        }
    }

    fun tap(position: Int) {
        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.filmItemLayout,
                recyclerViewId = R.id.dashboardRecyclerView
            )
        ).perform(click())
    }

    fun checkDashboardIsNotVisible() {
        recyclerViewInteraction.check(matches(not(isDisplayed())))
    }

}

class Film(
    private val title: String,
    private val rate: String,
    private val isFavorite: Boolean,
) {
    fun check(position: Int) {
        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.titleFilmTextView,
                recyclerViewId = R.id.dashboardRecyclerView
            )
        )
            .check(matches(withText(title)))

        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.rateTextView,
                recyclerViewId = R.id.dashboardRecyclerView
            )
        )

            .check(matches(withText(rate)))
        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.iconImageView,
                recyclerViewId = R.id.dashboardRecyclerView
            )
        )
            .check(
                matches(
                    DrawableMatcher(
                        if (isFavorite) R.drawable.ic_like_bookmark
                        else R.drawable.ic_unlike_bookmark
                    )
                )
            )
    }

}
