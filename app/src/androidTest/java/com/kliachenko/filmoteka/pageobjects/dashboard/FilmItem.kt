package com.kliachenko.filmoteka.pageobjects.dashboard

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.kliachenko.dashboard.R
import com.kliachenko.filmoteka.core.ImageViewDrawableMatcher
import com.kliachenko.filmoteka.core.RecyclerViewMatcher

class FilmItem(
    private val title: String,
    private val rate: String,
    private val isFavorite: Boolean,
) {

    private val recyclerViewId: Int = R.id.dashboardRecyclerView

    fun check(position: Int) {
        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.posterImageView,
                recyclerViewId = recyclerViewId
            )
        ).check(matches((isDisplayed())))

        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.titleFilmTextView,
                recyclerViewId = recyclerViewId
            )
        ).check(matches(ViewMatchers.withText(title)))

        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.rateTextView,
                recyclerViewId = recyclerViewId
            )
        ).check(matches(ViewMatchers.withText(rate)))

        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.rateFilmBar,
                recyclerViewId = recyclerViewId
            )
        ).check(matches((isDisplayed())))

        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.iconImageView,
                recyclerViewId = recyclerViewId
            )
        ).check(
            matches(
                ImageViewDrawableMatcher(
                    if (isFavorite) R.drawable.ic_bookmark
                    else R.drawable.ic_bookmark
                )
            )
        )
    }

}