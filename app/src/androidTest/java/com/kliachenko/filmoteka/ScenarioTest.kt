package com.kliachenko.filmoteka

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kliachenko.filmoteka.main.MainActivity
import com.kliachenko.filmoteka.pageobject.dashboard.DashboardPage
import com.kliachenko.filmoteka.pageobject.dashboard.FilmItem
import com.kliachenko.filmoteka.pageobject.main.MainPage
import org.junit.After
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
    private lateinit var detailPage: DetailPage

    @Before
    fun setup() {
        androidx.test.espresso.intent.Intents.init()
        dashboardPage = DashboardPage()
        mainPage = MainPage()
        detailPage = DetailPage()
    }

    @After
    fun tearDown() {
        androidx.test.espresso.intent.Intents.release()
    }

    @Test
    fun navigate_between_bottom_navigation_tabs() = with(mainPage) {
        checkMainState(activeTab = "Dashboard")
        activityScenarioRule.scenario.recreate()
        checkMainState(activeTab = "Dashboard")
        tapNavTab(tabName = "Search")
        checkMainState(activeTab = "Search")
        activityScenarioRule.scenario.recreate()
        checkMainState(activeTab = "Search")
        tapNavTab(tabName = "Favorites")
        checkMainState(activeTab = "Favorites")
        activityScenarioRule.scenario.recreate()
        checkMainState(activeTab = "Favorites")
        tapNavTab(tabName = "Dashboard")
        activityScenarioRule.scenario.recreate()
        tapNavTab(tabName = "Dashboard")
    }


    @Test
    fun load_dashboard_with_error_success_and_favorites_interaction() = with(dashboardPage) {
        checkDashboardProgressState(message = "loading...", tabName = "Popular")
        waitUntilDashboardProgressStateIsNotVisible()
        checkErrorState(errorMessage = "No internet connection", tabName = "Popular")
        activityScenarioRule.scenario.recreate()
        checkErrorState(errorMessage = "No internet connection", tabName = "Popular")
        tapRetryButton()
        checkDashboardProgressState(message = "loading...", tabName = "Popular")
        activityScenarioRule.scenario.recreate()
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
        activityScenarioRule.scenario.recreate()
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
        activityScenarioRule.scenario.recreate()
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
            )
        )
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
        activityScenarioRule.scenario.recreate()
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

    @Test
    fun load_detailScreen_from_dashboard() {
        detailPage.checkProgresState(message = "loading...")
        detailPage.waitUntilDashboardProgressStateIsNotVisible()
        detailPage.checkErrorState(
            errorMessage = "Error loading information.\n" +
                    "Please, try again..."
        )
        activityScenarioRule.scenario.recreate()
        detailPage.checkErrorState(
            errorMessage = "Error loading information.\n" +
                    "Please, try again..."
        )
        detailPage.tapRetryButton()
        detailPage.checkProgresState(message = "loading...")
        activityScenarioRule.scenario.recreate()
        detailPage.checkProgresState(message = "loading...")
        detailPage.waitUntilDashboardProgressStateIsNotVisible()
        detailPage.checkSuccessfulState(
            title = "Film#1",
            tagline = "Tagline Film#1",
            geners = listOf("Action", "Comedy"),
            releaseDate = "2022",
            runtime = "107",
            adult = false,
            score = "6,5",
            status = false,
            likeCount = 10,
            overView = "OverView",
            originalLanguage = "Eng",
            countyProduction = "USA",
            homePage = "http://film#1"
        )
        detailPage.tapFilmBookmarkIcon()
        detailPage.checkSuccessfulState(
            title = "Film#1",
            tagline = "Tagline Film#1",
            geners = listOf("Action", "Comedy"),
            releaseDate = "2022",
            runtime = "107",
            adult = false,
            score = "6,5",
            status = true,
            likeCount = 10,
            overView = "OverView",
            originalLanguage = "Eng",
            countyProduction = "USA",
            homePage = "http://film#1"
        )
        detailPage.tapFilmBookmarkIcon()
        detailPage.checkSuccessfulState(
            title = "Film#1",
            tagline = "Tagline Film#1",
            geners = listOf("Action", "Comedy"),
            releaseDate = "2022",
            runtime = "107",
            adult = false,
            score = "6,5",
            status = false,
            likeCount = 10,
            overView = "OverView",
            originalLanguage = "Eng",
            countyProduction = "USA",
            homePage = "http://film#1"
        )
        detailPage.clickFilmHomePage()

    }

}