package com.kliachenko.filmoteka.pageobjects.detail.success

import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.StatisticsUi
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class SuccessStateUi() {

    private val parentSuccessId: Matcher<View> = withParent(withId(R.id.successStateLayout))
    private val parentSuccessClass: Matcher<View> = withParent(isAssignableFrom(LinearLayout::class.java))
    private val interaction: ViewInteraction = onView(
        allOf(
            withParent(isAssignableFrom(ScrollView::class.java)),
            withParent(withId(R.id.scrollSuccessLayout))
        )
    )
    private val imagePosterView = ImagePosterView(parentSuccessId, parentSuccessClass)
    private val filmNameTextView = FilmNameTextView(parentSuccessId, parentSuccessClass)
    private val taglineTextView = TaglineTextView(parentSuccessId, parentSuccessClass)
    private val characteristicsTextView = CharacteristicsTextView(parentSuccessId, parentSuccessClass)
    private val statisticsUi = StatisticsUi(parentSuccessId, parentSuccessClass)
    private val overViewTextView = OverViewTextView(parentSuccessId, parentSuccessClass)
    private val originalLanguageTextView = OriginalLanguageTextView(parentSuccessId, parentSuccessClass)
    private val countryProductionTextView = CountryProductionTextView(parentSuccessId, parentSuccessClass)
    private val homePageTextView = HomePageTextView(parentSuccessId, parentSuccessClass)
    private val snackBarUi = SnackBarUi

    fun checkVisible(
        title: String,
        tagline: String,
        genres: List<String>,
        releaseDate: String,
        runtime: String,
        adult: Boolean,
        score: String,
        status: Boolean,
        likeCount: Int,
        overView: String,
        originalLanguage: String,
        countryProduction: String,
        homePage: String,
    ) {
        interaction.check(matches(isDisplayed()))
        imagePosterView.checkSuccess()
        filmNameTextView.checkSuccess(filmName = title)
        taglineTextView.checkSuccess(tagline = tagline)
        characteristicsTextView.checkSuccess(
            genres = genres,
            releaseDate = releaseDate,
            runtime = runtime,
            adult = adult
        )
        statisticsUi.checkSuccess(score = score, status = status, likeCount = likeCount)
        overViewTextView.checkSuccess(overView = overView)
        originalLanguageTextView.checkSuccess(text = originalLanguage)
        countryProductionTextView.checkSuccess(countryProduction = countryProduction)
        homePageTextView.checkSuccess()
    }

    fun tap(status: Boolean) {
        statisticsUi.tap()
        snackBarUi.checkSnackBarDisplayedWithText(expectedSelected = status)
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
