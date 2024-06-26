package com.kliachenko.filmoteka.pageobject

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.matchers.DrawableMatcher
import com.kliachenko.filmoteka.matchers.RecyclerViewMatcher
import org.hamcrest.Matcher

class DashboardPage {

    private val parentId: Matcher<View> = withParent(withId(R.id.dashboardLayout))
    private val parentClass: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))
    private val dashboardTabs = DashboarTabs(parentId = parentId, parentClass = parentClass)
    private val errorTextView = ErrorTextViewUi(parentId = parentId, parentClass = parentClass)
    private val progressBar = ProgressBarUi(parentId = parentId, parentClass = parentClass)
    private val retryButton = RetryButton(parentId = parentId, parentClass = parentClass)
    private val progressTextView = ProgressTextViewUi(parentId = parentId, parentClass = parentClass)
    private val filmsList = FilmsList(parentId = parentId, parentClass = parentClass)

    fun checkDashboardProgressState(message: String, tabName: String) {
        dashboardTabs.checkDashboardProgressState(tabName = tabName)
        errorTextView.checkDashboardProgressState()
        progressBar.checkDashboardProgressState()
        retryButton.checkDashboardProgressState()
        progressTextView.checkDashboardProgressState(message = message)
        filmsList.checkDashboardProgressState()
    }

    fun waitUntilDashboardProgressStateIsNotVisible() {
        progressBar.waitUntilIsNotVisible()
    }

    fun checkErrorState(errorMessage: String, tabName: String) {
        dashboardTabs.checkErrorState(tabName = tabName)
        errorTextView.checkErrorState()
        progressBar.checkErrorState()
        retryButton.checkErrorState()
        progressTextView.checkErrorState(message = errorMessage)
        filmsList.checkErrorState()
    }

    fun tapRetryButton() {
        retryButton.tap()
    }

    fun tapFilmBookmarkIcon(position: Int) {
        filmsList.tapBookmarkIcon(position = position)
    }

    fun checkSuccessfulState(films: List<Film>, tabName: String) {
        dashboardTabs.checkSuccessfulState(tabName = tabName)
        errorTextView.checkSuccessfulState()
        progressBar.checkSuccessfulState()
        retryButton.checkSuccessfulState()
        progressTextView.checkSuccessfulState()
        filmsList.checkSuccessfulState(films = films)
    }

    fun tapTab(tabName: String) {
        dashboardTabs.tap(tabName = tabName)
    }

    fun tapFilm(position: Int) {
        filmsList.tap(position = position)
    }

    fun checkDashboardIsNotVisible() {
        dashboardTabs.checkDashboardIsNotVisible()
        errorTextView.checkDashboardIsNotVisible()
        progressBar.checkDashboardIsNotVisible()
        retryButton.checkDashboardIsNotVisible()
        progressTextView.checkDashboardIsNotVisible()
        filmsList.checkDashboardIsNotVisible()
    }


}

class Film(
    private val title: String,
    private val rate: String,
    private val isFavorite: Boolean,
) {
    fun check(position: Int) {
        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.titleFilmTextView,
                recyclerViewId = R.id.dbRecyclerView
            )
        )
            .check(matches(withText(title)))

        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.rateTextView,
                recyclerViewId = R.id.dbRecyclerView
            )
        )

            .check(matches(withText(rate)))
        onView(
            RecyclerViewMatcher(
                position = position,
                targetViewId = R.id.iconImageView,
                recyclerViewId = R.id.dbRecyclerView
            )
        )
            .check(
                matches(
                    DrawableMatcher(
                        if (isFavorite) R.drawable.ic_like_bookmark
                        else R.drawable.ic_unlike_bookmark
                    )
                )
            )
    }

}
