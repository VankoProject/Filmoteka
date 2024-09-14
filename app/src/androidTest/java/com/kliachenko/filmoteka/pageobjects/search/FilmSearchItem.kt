package com.kliachenko.filmoteka.pageobjects.search

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.dashboard.presentation.customViews.CustomImageView
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class FilmSearchItem(private val title: String) {

    private val filmSearchItemId: Matcher<View> = withParent(withId(com.kliachenko.search.R.id.searchFilmItemLayout))
    private val filmSearchItemClass: Matcher<View> = withParent(isAssignableFrom(LinearLayout::class.java))

    fun checkSuccess() {
        onView(
            allOf(
                filmSearchItemId,
                filmSearchItemClass,
                withId(com.kliachenko.search.R.id.searchTitleTextView),
                isAssignableFrom(MaterialTextView::class.java),
                withText(title),
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                filmSearchItemId,
                filmSearchItemClass,
                withId(com.kliachenko.search.R.id.searchItemImageView),
                isAssignableFrom(CustomImageView::class.java)
            )
        ).check(matches(isDisplayed()))
    }
}
