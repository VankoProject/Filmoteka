package com.kliachenko.filmoteka.pageobject.dashboard

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.core.RecyclerViewMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class FilmsListUi(parentId: Matcher<View>, parentClass: Matcher<View>) {

    private val recyclerViewId: Int = R.id.dashboardRecyclerView

    private val recyclerViewInteraction: ViewInteraction = onView(
        allOf(
            parentId,
            parentClass,
            withId(recyclerViewId)
        )
    )

    private val filmItemInteraction: ViewInteraction = onView(
        allOf(
            withId(R.id.filmItemLayout),
            isAssignableFrom(ConstraintLayout::class.java),
            withParent(withId(recyclerViewId)),
            withParent(isAssignableFrom(RecyclerView::class.java))
        )
    )

    fun checkDashboardProgressState() {
        filmItemInteraction.check(matches(not(isDisplayed())))
    }

    fun checkErrorState() {
        filmItemInteraction.check(matches(not(isDisplayed())))
    }

    fun tapBookmarkIcon(position: Int) {
        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.iconImageView,
                recyclerViewId = recyclerViewId
            )
        ).perform(click())
    }

    fun checkSuccessfulState(films: List<FilmItem>) {
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
                recyclerViewId = recyclerViewId
            )
        ).perform(click())
    }

    fun checkDashboardIsNotVisible() {
        filmItemInteraction.check(matches(not(isDisplayed())))
        recyclerViewInteraction.check(matches(not(isDisplayed())))
    }

}

