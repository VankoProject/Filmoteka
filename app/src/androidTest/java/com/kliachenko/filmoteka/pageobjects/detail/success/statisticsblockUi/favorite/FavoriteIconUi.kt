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
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.core.ImageButtonDrawableMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class FavoriteIconUi(statisticsId: Matcher<View>, statisticsRootLayout: Matcher<View>) {

    private val favoriteDrawable = com.kliachenko.detail.R.drawable.ic_like_bookmark
    private val notFavoriteDrawable = com.kliachenko.detail.R.drawable.ic_unlike_bookmark
    private val favoriteIconId: Int = R.id.favoriteIconId
    private val interaction: ViewInteraction = onView(
        allOf(
            statisticsId, statisticsRootLayout,
            withId(favoriteIconId),
            isAssignableFrom(ImageButton::class.java)
        )
    )

    fun checkSuccess(status: Boolean) {
        interaction.apply {
            check(matches(isDisplayed()))
            val expectedIcon = if(status) favoriteDrawable else notFavoriteDrawable
            check(matches(ImageButtonDrawableMatcher(expectedIcon)))
        }
    }

    fun tap() {
        interaction.perform(click())
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
