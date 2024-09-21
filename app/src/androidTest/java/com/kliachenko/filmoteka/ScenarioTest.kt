package com.kliachenko.filmoteka

import android.content.Intent
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kliachenko.filmoteka.main.MainActivity
import com.kliachenko.filmoteka.pageobjects.dashboard.DashboardPage
import com.kliachenko.filmoteka.pageobjects.dashboard.FilmItem
import com.kliachenko.filmoteka.pageobjects.detail.DetailPage
import com.kliachenko.filmoteka.pageobjects.main.MainPage
import com.kliachenko.filmoteka.pageobjects.search.FilmSearchItem
import com.kliachenko.filmoteka.pageobjects.search.SearchPage
import org.hamcrest.Matchers.allOf
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
    private lateinit var searchPage: SearchPage

    @Before
    fun setup() {
        Intents.init()
        dashboardPage = DashboardPage()
        mainPage = MainPage()
        detailPage = DetailPage()
        searchPage = SearchPage()
    }

    @After
    fun tearDown() {
        Intents.release()
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
    fun load_detailScreen_withError_thenSuccess_thenAddToFavorite_thenRemoveFromFavorite() =
        with(detailPage) {
            checkProgressState(message = "loading...")
            waitUntilDashboardProgressStateIsNotVisible()
            checkErrorState(
                errorMessage = "Error loading information.\n" +
                        "Please, try again..."
            )
            activityScenarioRule.scenario.recreate()
            checkErrorState(
                errorMessage = "Error loading information.\n" +
                        "Please, try again..."
            )
            tapRetryButton()
            checkProgressState(message = "loading...")
            activityScenarioRule.scenario.recreate()
            checkProgressState(message = "loading...")
            waitUntilDashboardProgressStateIsNotVisible()
            checkSuccessfulState(
                title = "Film#1",
                tagline = "Tagline Film#1",
                genres = listOf("Action", "Comedy"),
                releaseDate = "2022",
                runtime = "107",
                adult = false,
                score = "6,5",
                status = false,
                likeCount = 10,
                overView = "OverView",
                originalLanguage = "Eng",
                countryProduction = "USA",
                homePage = "https://www.google.com/"
            )
            tapFilmBookmarkIcon(status = false)
            checkSuccessfulState(
                title = "Film#1",
                tagline = "Tagline Film#1",
                genres = listOf("Action", "Comedy"),
                releaseDate = "2022",
                runtime = "107",
                adult = false,
                score = "6,5",
                status = true,
                likeCount = 10,
                overView = "OverView",
                originalLanguage = "Eng",
                countryProduction = "USA",
                homePage = "https://www.google.com/"
            )
            activityScenarioRule.scenario.recreate()
            checkSuccessfulState(
                title = "Film#1",
                tagline = "Tagline Film#1",
                genres = listOf("Action", "Comedy"),
                releaseDate = "2022",
                runtime = "107",
                adult = false,
                score = "6,5",
                status = true,
                likeCount = 10,
                overView = "OverView",
                originalLanguage = "Eng",
                countryProduction = "USA",
                homePage = "https://www.google.com/"
            )
            tapFilmBookmarkIcon(status = true)
            checkSuccessfulState(
                title = "Film#1",
                tagline = "Tagline Film#1",
                genres = listOf("Action", "Comedy"),
                releaseDate = "2022",
                runtime = "107",
                adult = false,
                score = "6,5",
                status = false,
                likeCount = 10,
                overView = "OverView",
                originalLanguage = "Eng",
                countryProduction = "USA",
                homePage = "https://www.google.com/"
            )

            clickFilmHomePage()

            intended(
                allOf(
                    hasAction(Intent.ACTION_VIEW),
                    hasData("https://www.google.com/")
                )
            )
            tapBack()
        }


    @Test
    fun searchPage_withEmptyHistory_noResults_thenError_thenSuccess_thenHistory() {
        searchPage.checkInitialState(titleMessage = "Start searching to see your history here")
        searchPage.type(text = "A")
        searchPage.checkProgressState(progressMessage = "Continue typing...")
        searchPage.type(text = "Ab")
        searchPage.checkProgressState(progressMessage = "Continue typing...")
        searchPage.type(text = "Abc")
        searchPage.checkProgressState(progressMessage = "Searching...")
        searchPage.checkInitialState(titleMessage = "Unfortunately, nothing was found")
        searchPage.tapClearAllInput()
        searchPage.checkInitialState(titleMessage = "Start searching to see your history here")
        searchPage.type(text = "F")
        searchPage.checkProgressState(progressMessage = "Continue typing...")
        searchPage.type(text = "Fi")
        searchPage.checkProgressState(progressMessage = "Continue typing...")
        searchPage.type(text = "Fil")
        searchPage.checkProgressState(progressMessage = "Searching...")
        searchPage.checkErrorState(errorMessage = "No internet connection")
        searchPage.retry()
        searchPage.checkSuccessfulState(
            subscriptionText = "Search results",
            filmSearchItems = listOf(
                FilmSearchItem(title = "Film#1"),
                FilmSearchItem(title = "Film#2"),
                FilmSearchItem(title = "Film#3")
            )
        )
        searchPage.tapFilm(position = 0)
        detailPage.checkSuccessfulState(
            title = "Film#1",
            tagline = "Tagline Film#1",
            genres = listOf("Action", "Comedy"),
            releaseDate = "2022",
            runtime = "107",
            adult = false,
            score = "6,5",
            status = false,
            likeCount = 10,
            overView = "OverView",
            originalLanguage = "Eng",
            countryProduction = "USA",
            homePage = "https://www.google.com/"
        )
        detailPage.tapBack()
        searchPage.checkSuccessfulState(
            subscriptionText = "Recent",
            filmSearchItems = listOf(
                FilmSearchItem(title = "Film#1"),
            )
        )
        searchPage.tapFilm(position = 0)
    }

}