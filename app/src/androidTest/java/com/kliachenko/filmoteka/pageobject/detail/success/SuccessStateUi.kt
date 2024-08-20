package com.kliachenko.filmoteka.pageobject.detail.success

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.filmoteka.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class SuccessStateUi(parentId: Matcher<View>, parentClass: Matcher<View>) {

    private val parentSuccessId: Matcher<View> = withParent(withId(R.id.successStateLayout))
    private val parentSuccessClass: Matcher<View> = withParent(isAssignableFrom(LinearLayout::class.java))
    private val interaction: ViewInteraction = onView(
        allOf(
            parentId,
            parentClass
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
    private val snackBarUi = SnackBarUi(parentSuccessId, parentSuccessClass)

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
        taglineTextView.checkSuccess(text = tagline)
        characteristicsTextView.checkSuccess(
            genres = genres,
            releaseDate = releaseDate,
            runtime = runtime,
            adult = adult
        )
        statisticsUi.checkSuccess(score = score, status = status, likeCount = likeCount)
        overViewTextView.checkSuccess(overView = overView)
        originalLanguageTextView.checkSuccess(originalLanguage = originalLanguage)
        countryProductionTextView.checkSuccess(countryProduction = countryProduction)
        homePageTextView.checkSuccess(homePage = homePage)
    }

    fun tap(status: Boolean) {
        statisticsUi.tap(expectedSelected = status)
        snackBarUi.checkSnackBarDisplayedWithText()
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
