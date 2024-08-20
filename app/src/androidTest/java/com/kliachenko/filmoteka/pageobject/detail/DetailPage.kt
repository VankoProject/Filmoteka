package com.kliachenko.filmoteka.pageobject.detail

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.pageobject.detail.error.ErrorStateUi
import com.kliachenko.filmoteka.pageobject.detail.progress.ProgressStateUi
import com.kliachenko.filmoteka.pageobject.detail.success.SuccessStateUi
import org.hamcrest.Matcher

class DetailPage {

    private val parentId: Matcher<View> = withParent(withId(R.id.rootDetailLayout))
    private val parentClass: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val toolBarView = ToolBarView(parentId, parentClass)
    private val errorStateUi = ErrorStateUi(parentId, parentClass)
    private val progressStateUi = ProgressStateUi(parentId, parentClass)
    private val successStateUi = SuccessStateUi()

    fun checkProgressState(message: String) {
        toolBarView.checkInitial()
        errorStateUi.isNotVisible()
        progressStateUi.checkVisible(message)
        successStateUi.isNotVisible()
    }

    fun waitUntilDashboardProgressStateIsNotVisible() {
        progressStateUi.waitUntilIsNotVisible()
    }

    fun checkErrorState(errorMessage: String) {
        toolBarView.checkInitial()
        progressStateUi.isNotVisible()
        errorStateUi.checkVisible(errorMessage)
        successStateUi.isNotVisible()
    }

    fun tapRetryButton() {
        errorStateUi.tap()
    }

    fun checkSuccessfulState(
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
        toolBarView.checkSuccess(title = title)
        progressStateUi.isNotVisible()
        errorStateUi.isNotVisible()
        successStateUi.checkVisible(
            title,
            tagline,
            genres,
            releaseDate,
            runtime,
            adult,
            score,
            status,
            likeCount,
            overView,
            originalLanguage,
            countryProduction,
            homePage
        )
    }

    fun tapFilmBookmarkIcon(status: Boolean) {
        successStateUi.tap(status)
    }

    fun clickFilmHomePage() {
        toolBarView.tapBack()
    }

    fun tapBack() {
        toolBarView.isNotVisible()
        errorStateUi.isNotVisible()
        progressStateUi.isNotVisible()
        successStateUi.isNotVisible()
    }

}