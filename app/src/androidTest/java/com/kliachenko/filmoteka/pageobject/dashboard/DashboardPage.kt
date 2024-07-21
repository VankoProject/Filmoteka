package com.kliachenko.filmoteka.pageobject.dashboard

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.dashboard.R
import org.hamcrest.Matcher

class DashboardPage {

    private val parentId: Matcher<View> = withParent(withId(R.id.dashboardTabs))
    private val parentClass: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))
    private val dashboardTabsUi = DashboardTabsUi(
        parentId = parentId,
        parentClass = parentClass,
        tabs = listOf("Popular", "Top rated", "Upcoming"),
        tabsIds = listOf(R.id.popular_tab, R.id.top_rated_tab, R.id.upcoming_tab)
    )
    private val errorTextViewUi = ErrorTextViewUi(parentId, parentClass)
    private val progressBarUi = ProgressBarUi(parentId, parentClass)
    private val retryButtonUi = RetryButtonUi(parentId, parentClass)
    private val progressTextViewUi = ProgressTextViewUi(parentId, parentClass)
    private val filmsListUi = FilmsListUi(parentId, parentClass)

    fun checkDashboardProgressState(message: String, tabName: String) {
        dashboardTabsUi.checkVisible(activeTab = tabName)
        errorTextViewUi.checkDashboardProgressState()
        progressBarUi.checkDashboardProgressState()
        retryButtonUi.checkDashboardProgressState()
        progressTextViewUi.checkDashboardProgressState(message = message)
        filmsListUi.checkDashboardProgressState()
    }

    fun waitUntilDashboardProgressStateIsNotVisible() {
        progressBarUi.waitUntilIsNotVisible()
    }

    fun checkErrorState(errorMessage: String, tabName: String) {
        dashboardTabsUi.checkVisible(activeTab = tabName)
        errorTextViewUi.checkErrorState(message = errorMessage)
        progressBarUi.checkErrorState()
        retryButtonUi.checkErrorState()
        progressTextViewUi.checkErrorState()
        filmsListUi.checkErrorState()
    }

    fun tapRetryButton() {
        retryButtonUi.tap()
    }

    fun tapFilmBookmarkIcon(position: Int) {
        filmsListUi.tapBookmarkIcon(position = position)
    }

    fun checkSuccessfulState(filmItems: List<FilmItem>, tabName: String) {
        dashboardTabsUi.checkVisible(activeTab = tabName)
        errorTextViewUi.checkSuccessfulState()
        progressBarUi.checkSuccessfulState()
        retryButtonUi.checkSuccessfulState()
        progressTextViewUi.checkSuccessfulState()
        filmsListUi.checkSuccessfulState(films = filmItems)
    }

    fun tapTab(tabName: String) {
        dashboardTabsUi.tap(tabName = tabName)
    }

    fun tapFilm(position: Int) {
        filmsListUi.tap(position = position)
    }

    fun checkDashboardIsNotVisible() {
        dashboardTabsUi.checkDashboardIsNotVisible()
        errorTextViewUi.checkDashboardIsNotVisible()
        progressBarUi.checkDashboardIsNotVisible()
        retryButtonUi.checkDashboardIsNotVisible()
        progressTextViewUi.checkDashboardIsNotVisible()
        filmsListUi.checkDashboardIsNotVisible()
    }
}
