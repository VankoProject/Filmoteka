package com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.like

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

class LikeIconUi(statisticsId: Matcher<View>, statisticsRootLayout: Matcher<View>) {

    private val likeIconLayoutId: Matcher<View> =
        withId(com.kliachenko.detail.R.id.likeIconLayoutId)
    private val likeLayout: Matcher<View> = isAssignableFrom(FrameLayout::class.java)
    private val interaction: ViewInteraction = onView(
        allOf(
            statisticsId, statisticsRootLayout,
            likeIconLayoutId,
            likeLayout
        )
    )
    private val likeIconBackground = LikeIconBackground(likeIconLayoutId, likeLayout)
    private val likeIcon = LikeIcon(likeIconLayoutId, likeLayout)

    fun checkSuccess(status: Boolean) {
        interaction.check(matches(isDisplayed()))
        likeIconBackground.checkSuccess(status)
        likeIcon.checkSuccess()
    }

    fun tap() {
        likeIcon.tap()
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
        likeIconBackground.isNotVisible()
        likeIcon.isNotVisible()
    }

}
