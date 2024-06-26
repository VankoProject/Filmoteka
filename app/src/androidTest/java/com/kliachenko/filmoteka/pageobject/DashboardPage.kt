package com.kliachenko.filmoteka.pageobject

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.filmoteka.R
import org.hamcrest.Matcher

class DashboardPage {

    private val parentId: Matcher<View> = withParent(withId(R.id.dashboardLayout))
    private val parentClass: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))
    private val dashboardTabsUi = DashboardTabsUi(
        parentId = parentId,
        parentClass = parentClass,
        tabs = listOf("Popular", "Top rated", "Upcoming"),
        tabsIds = listOf(R.id.popularTab, R.id.topRatedTab, R.id.upcomingTab))
    private val errorTextView = ErrorTextViewUi(parentId = parentId, parentClass = parentClass)
    private val progressBar = ProgressBarUi(parentId = parentId, parentClass = parentClass)
    private val retryButtonUi = RetryButtonUi(parentId = parentId, parentClass = parentClass)
    private val progressTextView =
        ProgressTextViewUi(parentId = parentId, parentClass = parentClass)
    private val filmsListUi = FilmsListUi(parentId = parentId, parentClass = parentClass)

    fun checkDashboardProgressState(message: String, tabName: String) {
        dashboardTabsUi.checkVisible(
            activeTab = tabName
        )
        errorTextView.checkDashboardProgressState()
        progressBar.checkDashboardProgressState()
        retryButtonUi.checkDashboardProgressState()
        progressTextView.checkDashboardProgressState(message = message)
        filmsListUi.checkDashboardProgressState()
    }

    fun waitUntilDashboardProgressStateIsNotVisible() {
        progressBar.waitUntilIsNotVisible()
    }

    fun checkErrorState(errorMessage: String, tabName: String) {
        dashboardTabsUi.checkVisible(
            activeTab = tabName
        )
        errorTextView.checkErrorState(message = errorMessage)
        progressBar.checkErrorState()
        retryButtonUi.checkErrorState()
        progressTextView.checkErrorState()
        filmsListUi.checkErrorState()
    }

    fun tapRetryButton() {
        retryButtonUi.tap()
    }

    fun tapFilmBookmarkIcon(position: Int) {
        filmsListUi.tapBookmarkIcon(position = position)
    }

    fun checkSuccessfulState(films: List<Film>, tabName: String) {
        dashboardTabsUi.checkVisible(
            activeTab = tabName
        )
        errorTextView.checkSuccessfulState()
        progressBar.checkSuccessfulState()
        retryButtonUi.checkSuccessfulState()
        progressTextView.checkSuccessfulState()
        filmsListUi.checkSuccessfulState(films = films)
    }

    fun tapTab(tabName: String) {
        dashboardTabsUi.tap(tabName = tabName)
    }

    fun tapFilm(position: Int) {
        filmsListUi.tap(position = position)
    }

    fun checkDashboardIsNotVisible() {
        dashboardTabsUi.checkDashboardIsNotVisible()
        errorTextView.checkDashboardIsNotVisible()
        progressBar.checkDashboardIsNotVisible()
        retryButtonUi.checkDashboardIsNotVisible()
        progressTextView.checkDashboardIsNotVisible()
        filmsListUi.checkDashboardIsNotVisible()
    }


}
