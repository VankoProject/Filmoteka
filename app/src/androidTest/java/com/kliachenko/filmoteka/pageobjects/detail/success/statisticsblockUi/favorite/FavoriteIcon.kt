package com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.favorite

import android.view.View
import android.widget.ImageButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.kliachenko.filmoteka.core.ImageButtonDrawableMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class FavoriteIcon(favoriteUiId: Matcher<View>, favoriteLayout: Matcher<View>) {

    private val favoriteIconId: Matcher<View> = withId(com.kliachenko.detail.R.id.favoriteIconId)
    private val favoriteDrawableId = com.kliachenko.detail.R.drawable.ic_unfavorite_bookmark
    private val interaction: ViewInteraction = onView(
        allOf(
            favoriteUiId, favoriteLayout,
            favoriteIconId, isAssignableFrom(ImageButton::class.java)
        )
    )
    fun checkSuccess() {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(ImageButtonDrawableMatcher(favoriteDrawableId)))
        }
    }

    fun tap() {
        interaction.perform(click())
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
