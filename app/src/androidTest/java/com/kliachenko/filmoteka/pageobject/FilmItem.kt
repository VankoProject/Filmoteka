package com.kliachenko.filmoteka.pageobject

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.matchers.DrawableMatcher
import com.kliachenko.filmoteka.matchers.RecyclerViewMatcher

class FilmItem(
    private val title: String,
    private val rate: String,
    private val isFavorite: Boolean,
) {

    fun check(position: Int) {
        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.posterImageView,
                recyclerViewId = R.id.dashboardRecyclerView
            )
        ).check(matches((isDisplayed())))

        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.titleFilmTextView,
                recyclerViewId = R.id.dashboardRecyclerView
            )
        ).check(matches(ViewMatchers.withText(title)))

        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.rateTextView,
                recyclerViewId = R.id.dashboardRecyclerView
            )
        ).check(matches(ViewMatchers.withText(rate)))

        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.rateFilmBar,
                recyclerViewId = R.id.dashboardRecyclerView
            )
        ).check(matches((isDisplayed())))

        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.iconImageView,
                recyclerViewId = R.id.dashboardRecyclerView
            )
        ).check(
            matches(
                DrawableMatcher(
                    if (isFavorite) R.drawable.ic_like_bookmark
                    else R.drawable.ic_unlike_bookmark
                )
            )
        )
    }

}