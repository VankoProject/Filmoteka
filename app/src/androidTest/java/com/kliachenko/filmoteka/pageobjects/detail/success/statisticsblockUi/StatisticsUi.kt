package com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.favorite.FavoriteIconUi
import com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.favorite.FavoriteTextUi
import com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.like.LikeCountTextUi
import com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.like.LikeIconUi
import com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.score.ScoreIconUi
import com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.score.ScoreTextUi
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class StatisticsUi(parentSuccessId: Matcher<View>, parentSuccessClass: Matcher<View>) {

    private val statisticsId: Matcher<View> = withId(com.kliachenko.detail.R.id.statisticsLayout)
    private val statisticsRootLayout: Matcher<View> = isAssignableFrom(ConstraintLayout::class.java)
    private val interaction: ViewInteraction = onView(
        allOf(
            parentSuccessId, parentSuccessClass,
            statisticsId, statisticsRootLayout
        )
    )
    private val scoreIconUi = ScoreIconUi(statisticsId, statisticsRootLayout)
    private val scoreTextUi = ScoreTextUi(statisticsId, statisticsRootLayout)
    private val favoriteIconUi = FavoriteIconUi(statisticsId, statisticsRootLayout)
    private val favoriteTextUi = FavoriteTextUi(statisticsId, statisticsRootLayout)
    private val likeIconUi = LikeIconUi(statisticsId, statisticsRootLayout)
    private val likeCountTextUi = LikeCountTextUi(statisticsId, statisticsRootLayout)


    fun checkSuccess(score: String, status: Boolean, likeCount: Int) {
        interaction.check(matches(isDisplayed()))
        scoreIconUi.checkSuccess(score = score)
        scoreTextUi.checkSuccess()
        favoriteIconUi.checkSuccess(status = status)
        favoriteTextUi.checkSuccess()
        likeIconUi.checkSuccess()
        likeCountTextUi.checkSuccess(likeCount = likeCount)
    }

    fun tap() {
        favoriteIconUi.tap()
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
        scoreIconUi.isNotVisible()
        scoreTextUi.isNotVisible()
        favoriteIconUi.isNotVisible()
        favoriteTextUi.isNotVisible()
        likeIconUi.isNotVisible()
        likeCountTextUi.isNotVisible()
    }

}
