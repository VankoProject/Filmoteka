package com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.favorite

import android.view.View
import android.widget.FrameLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class FavoriteIconUi(statisticsId: Matcher<View>, statisticsRootLayout: Matcher<View>) {

    private val favoriteIconLayoutId: Matcher<View> = withId(com.kliachenko.detail.R.id.favoriteIconLayoutId)
    private val favoriteLayout: Matcher<View> = isAssignableFrom(FrameLayout::class.java)
    private val interaction: ViewInteraction = onView(
        allOf(
            statisticsId, statisticsRootLayout,
            favoriteIconLayoutId,
            favoriteLayout
        )
    )

    private val favoriteIconBackground = FavoriteIconBackground(favoriteIconLayoutId, favoriteLayout)
    private val favoriteIcon = FavoriteIcon(favoriteIconLayoutId, favoriteLayout)

    fun checkSuccess(status: Boolean) {
        interaction.check(matches(isDisplayed()))
        favoriteIconBackground.checkSuccess(status)
        favoriteIcon.checkSuccess()
    }

    fun tap() {
        favoriteIcon.tap()
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
        favoriteIconBackground.isNotVisible()
    }

}
