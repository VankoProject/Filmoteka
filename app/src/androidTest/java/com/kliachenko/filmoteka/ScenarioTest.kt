package com.kliachenko.filmoteka

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kliachenko.filmoteka.pageobject.dashboard.DashboardPage
import com.kliachenko.filmoteka.pageobject.dashboard.FilmItem
import com.kliachenko.filmoteka.pageobject.main.MainPage
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var mainPage: MainPage
    private lateinit var dashboardPage: DashboardPage

    @Before
    fun setup() {
        dashboardPage = DashboardPage()
        mainPage = MainPage()
    }

    @Test
    fun `navigate between bottom navigation tabs`() = with(mainPage) {
        checkMainState(activeTab = "Dashboard")
        tapNavTab(tabName = "Search")
        checkMainState(activeTab = "Search")
        tapNavTab(tabName = "Favorites")
        checkMainState(activeTab = "Favorites")
        tapNavTab(tabName = "Dashboard")
    }


    @Test
    fun `load dashboard with error success and favorites interaction`() = with(dashboardPage) {
        checkDashboardProgressState(message = "loading...", tabName = "Popular")
        waitUntilDashboardProgressStateIsNotVisible()
        checkErrorState(errorMessage = "No internet connection", tabName = "Popular")
        tapRetryButton()
        checkDashboardProgressState(message = "loading...", tabName = "Popular")
        waitUntilDashboardProgressStateIsNotVisible()
        checkSuccessfulState(
            tabName = "Popular",
            filmItems = listOf(
                FilmItem(title = "Film#1", rate = "1.0", isFavorite = false),
                FilmItem(title = "Film#2", rate = "2.0", isFavorite = false),
                FilmItem(title = "Film#3", rate = "3.0", isFavorite = false),
                FilmItem(title = "Film#4", rate = "4.0", isFavorite = false),
            )
        )
        tapTab(tabName = "Top rated")
        checkDashboardProgressState(message = "loading...", tabName = "Top rated")
        waitUntilDashboardProgressStateIsNotVisible()
        checkErrorState(errorMessage = "No internet connection", tabName = "Top rated")
        tapRetryButton()
        checkDashboardProgressState(message = "loading...", tabName = "Top rated")
        waitUntilDashboardProgressStateIsNotVisible()
        checkSuccessfulState(
            tabName = "Top rated",
            filmItems = listOf(
                FilmItem(title = "Film#1", rate = "1.0", isFavorite = false),
                FilmItem(title = "Film#2", rate = "2.0", isFavorite = false),
                FilmItem(title = "Film#3", rate = "3.0", isFavorite = false),
                FilmItem(title = "Film#4", rate = "4.0", isFavorite = false),
                ))
        tapTab(tabName = "Upcoming")
        checkDashboardProgressState(message = "loading...", tabName = "Upcoming")
        waitUntilDashboardProgressStateIsNotVisible()
        checkErrorState(errorMessage = "No internet connection", tabName = "Upcoming")
        tapRetryButton()
        checkDashboardProgressState(message = "loading...", tabName = "Upcoming")
        waitUntilDashboardProgressStateIsNotVisible()
        checkSuccessfulState(
            tabName = "Upcoming",
            filmItems = listOf(
                FilmItem(title = "Film#1", rate = "1.0", isFavorite = false),
                FilmItem(title = "Film#2", rate = "2.0", isFavorite = false),
                FilmItem(title = "Film#3", rate = "3.0", isFavorite = false),
                FilmItem(title = "Film#4", rate = "4.0", isFavorite = false),
            )
        )
        tapFilmBookmarkIcon(position = 0)
        checkSuccessfulState(
            tabName = "Upcoming",
            filmItems = listOf(
                FilmItem(title = "Film#1", rate = "1.0", isFavorite = true),
                FilmItem(title = "Film#2", rate = "2.0", isFavorite = false),
                FilmItem(title = "Film#3", rate = "3.0", isFavorite = false),
                FilmItem(title = "Film#4", rate = "4.0", isFavorite = false),
            )
        )
        tapFilmBookmarkIcon(position = 0)
        checkSuccessfulState(
            tabName = "Upcoming",
            filmItems = listOf(
                FilmItem(title = "Film#1", rate = "1.0", isFavorite = false),
                FilmItem(title = "Film#2", rate = "2.0", isFavorite = false),
                FilmItem(title = "Film#3", rate = "3.0", isFavorite = false),
                FilmItem(title = "Film#4", rate = "4.0", isFavorite = false),
            )
        )
        tapTab(tabName = "Popular")
        checkSuccessfulState(
            tabName = "Popular",
            filmItems = listOf(
                FilmItem(title = "Film#1", rate = "1.0", isFavorite = false),
                FilmItem(title = "Film#2", rate = "2.0", isFavorite = false),
                FilmItem(title = "Film#3", rate = "3.0", isFavorite = false),
                FilmItem(title = "Film#4", rate = "4.0", isFavorite = false),
            )
        )
        tapFilm(position = 1)
        checkDashboardIsNotVisible()
    }

}