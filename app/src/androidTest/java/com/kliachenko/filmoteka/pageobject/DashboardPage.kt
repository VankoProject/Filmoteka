package com.kliachenko.filmoteka.pageobject

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.filmoteka.R
import org.hamcrest.Matcher

class DashboardPage {

    private val parentIdMatcher: Matcher<View> = withParent(withId(R.id.dashboardLayout))
    private val parentClassMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))
    private val tabsUi = TabsUi()
    private val errorTextViewUi = ErrorTextViewUi()
    private val progressBarUi = ProgressBarUi()
    private val progressTextViewUi = ProgressTextViewUi()
    private val filmItemUi = FilmItemUi()

    fun checkDashboardProgressState(message: String, tabName: String) {
        TODO("Not yet implemented")
    }

    fun waitUntilDashboardProgressStateIsNotVisible() {
        TODO("Not yet implemented")
    }

    fun checkErrorState(errorMessage: String) {
        TODO("Not yet implemented")
    }

    fun tapRetryButton() {
        TODO("Not yet implemented")
    }

    fun tapFilmBookmarkIcon(position: Int) {
        TODO("Not yet implemented")
    }

    fun checkSuccessfulState(films: List<Film>) {
        TODO("Not yet implemented")
    }

    fun tapTab(tabName: String) {
        TODO("Not yet implemented")
    }

    fun tapFilm(position: Int) {
        TODO("Not yet implemented")
    }

    fun checkNavigateToDetailScreen() {
        TODO("Not yet implemented")
    }




}

class Film(
    private val title: String,
    private val rate: String,
    private val isFavorite: Boolean
){

}
