package com.kliachenko.filmoteka

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kliachenko.filmoteka.pageobject.DashboardPage
import com.kliachenko.filmoteka.pageobject.Film
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var dashboardPage: DashboardPage

    @Before
    fun setup() {
        dashboardPage = DashboardPage()
    }


    @Test
    fun `load_dashboard_with_error_success_and_favorites_interaction`() = with(dashboardPage) {
        checkDashboardProgressState(message = "loading...", tabName = "Popular")
        waitUntilDashboardProgressStateIsNotVisible()
        checkErrorState(errorMessage = "No internet connection")
        tapRetryButton()
        checkDashboardProgressState(message = "loading...", tabName = "Popular")
        waitUntilDashboardProgressStateIsNotVisible()
        checkSuccessfulState(
            films = listOf(
                Film(title = "Film#1", rate = "1.0", isFavorite = false),
                Film(title = "Film#2", rate = "2.0", isFavorite = false),
                Film(title = "Film#3", rate = "3.0", isFavorite = false),
                Film(title = "Film#4", rate = "4.0", isFavorite = false),
            )
        )
        tapTab(tabName = "Top rated")
        checkDashboardProgressState(message = "loading...", tabName = "Top rated")
        waitUntilDashboardProgressStateIsNotVisible()
        checkErrorState(errorMessage = "No internet connection")
        tapRetryButton()
        checkDashboardProgressState(message = "loading...", tabName = "Top rated")
        waitUntilDashboardProgressStateIsNotVisible()
        checkSuccessfulState(
            films = listOf(
                Film(title = "Film#1", rate = "1.0", isFavorite = false),
                Film(title = "Film#2", rate = "2.0", isFavorite = false),
                Film(title = "Film#3", rate = "3.0", isFavorite = false),
                Film(title = "Film#4", rate = "4.0", isFavorite = false),
                ))
        tapTab(tabName = "Upcoming")
        checkDashboardProgressState(message = "loading...", tabName = "Upcoming")
        waitUntilDashboardProgressStateIsNotVisible()
        checkErrorState(errorMessage = "No internet connection")
        tapRetryButton()
        checkDashboardProgressState(message = "loading...", tabName = "Upcoming")
        waitUntilDashboardProgressStateIsNotVisible()
        checkSuccessfulState(
            films = listOf(
                Film(title = "Film#1", rate = "1.0", isFavorite = false),
                Film(title = "Film#2", rate = "2.0", isFavorite = false),
                Film(title = "Film#3", rate = "3.0", isFavorite = false),
                Film(title = "Film#4", rate = "4.0", isFavorite = false),
            )
        )
        tapFilmBookmarkIcon(position = 0)
        checkSuccessfulState(
            films = listOf(
                Film(title = "Film#1", rate = "1.0", isFavorite = true),
                Film(title = "Film#2", rate = "2.0", isFavorite = false),
                Film(title = "Film#3", rate = "3.0", isFavorite = false),
                Film(title = "Film#4", rate = "4.0", isFavorite = false),
            )
        )
        tapFilmBookmarkIcon(position = 0)
        checkSuccessfulState(
            films = listOf(
                Film(title = "Film#1", rate = "1.0", isFavorite = false),
                Film(title = "Film#2", rate = "2.0", isFavorite = false),
                Film(title = "Film#3", rate = "3.0", isFavorite = false),
                Film(title = "Film#4", rate = "4.0", isFavorite = false),
            )
        )
        tapTab(tabName = "Popular")
        checkSuccessfulState(
            films = listOf(
                Film(title = "Film#1", rate = "1.0", isFavorite = false),
                Film(title = "Film#2", rate = "2.0", isFavorite = false),
                Film(title = "Film#3", rate = "3.0", isFavorite = false),
                Film(title = "Film#4", rate = "4.0", isFavorite = false),
            )
        )
        tapFilm(position = 1)
        checkNavigateToDetailScreen()
    }

}