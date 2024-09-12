package com.kliachenko.filmoteka.pageobjects.search

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.filmoteka.R
import org.hamcrest.Matcher

class SearchPage {

    private val parentId: Matcher<View> = withParent(withId(R.id.rootSearchLayout))
    private val parentClass: Matcher<View> = withParent(isAssignableFrom(LinearLayout::class.java))
    private val searchViewUi = SearchViewUi(parentId, parentClass)
    private val initialSearchStateUi = InititalSearchStateUi(parentId, parentClass)
    private val historySearchStateUi = HistorySearchStateUi(parentId, parentId)
    private val invalidSearchStateUi = InvalidSearchStateUi(parentId, parentClass)
    private val validSearchStateUi = ValidSearchStateUi(parentId, parentClass)
    private val errorSearchStateUi = ErrorSearchStateUi(parentId, parentClass)
    private val successSearchStateUi = SuccessSearchStateUi(parentId, parentClass)
    private val blankSearchStateUi = BlankSearchStateUi(parentId, parentClass)

    fun checkInitialState() {
        searchViewUi.checkInitial()
        initialSearchStateUi.checkVisible()
        historySearchStateUi.isNotVisible()
        invalidSearchStateUi.isNotVisible()
        validSearchStateUi.isNotVisible()
        errorSearchStateUi.isNotVisible()
        successSearchStateUi.isNotVisible()
        blankSearchStateUi.isNotVisible()
    }

    fun type(text: String) {
        searchViewUi.type(text = text)
    }

    fun checkInvalidSearchState(message: String) {
        initialSearchStateUi.isNotVisible()
        historySearchStateUi.isNotVisible()
        invalidSearchStateUi.checkVisible(message = message)
        validSearchStateUi.isNotVisible()
        errorSearchStateUi.isNotVisible()
        successSearchStateUi.isNotVisible()
        blankSearchStateUi.isNotVisible()
    }

    fun checkValidSearchState(message: String) {
        initialSearchStateUi.isNotVisible()
        historySearchStateUi.isNotVisible()
        invalidSearchStateUi.isNotVisible()
        validSearchStateUi.checkVisible(message = message)
        errorSearchStateUi.isNotVisible()
        successSearchStateUi.isNotVisible()
        blankSearchStateUi.isNotVisible()
    }

    fun checkBlankState(message: String) {
        initialSearchStateUi.isNotVisible()
        historySearchStateUi.isNotVisible()
        invalidSearchStateUi.isNotVisible()
        validSearchStateUi.isNotVisible()
        errorSearchStateUi.isNotVisible()
        successSearchStateUi.isNotVisible()
        blankSearchStateUi.checkVisible(message = message)
    }

    fun tapClearAllInput() {
        searchViewUi.tapClearAllInput()
    }

    fun checkErrorState(errorMessage: String) {
        initialSearchStateUi.isNotVisible()
        historySearchStateUi.isNotVisible()
        invalidSearchStateUi.isNotVisible()
        validSearchStateUi.isNotVisible()
        errorSearchStateUi.checkVisible(errorMessage = errorMessage)
        successSearchStateUi.isNotVisible()
        blankSearchStateUi.isNotVisible()
    }

    fun retry() {
        errorSearchStateUi.retry()
    }

    fun checkSuccessfulState(filmSearchItems: List<FilmSearchItem>) {
        initialSearchStateUi.isNotVisible()
        historySearchStateUi.isNotVisible()
        invalidSearchStateUi.isNotVisible()
        validSearchStateUi.isNotVisible()
        errorSearchStateUi.isNotVisible()
        successSearchStateUi.checkVisible(films = filmSearchItems)
        blankSearchStateUi.isNotVisible()
    }

    fun tapSearchFilm(position: Int) {
        successSearchStateUi.tapFilm(position = position)
    }

    fun checkHistoryState(filmSearchItems: List<FilmSearchItem>) {
        initialSearchStateUi.isNotVisible()
        historySearchStateUi.checkVisible(films = filmSearchItems)
        invalidSearchStateUi.isNotVisible()
        validSearchStateUi.isNotVisible()
        errorSearchStateUi.isNotVisible()
        successSearchStateUi.isNotVisible()
        blankSearchStateUi.isNotVisible()
    }

    fun tapSearchFilmFromHistory(position: Int) {
        historySearchStateUi.tapFilm(position = position)
    }
}