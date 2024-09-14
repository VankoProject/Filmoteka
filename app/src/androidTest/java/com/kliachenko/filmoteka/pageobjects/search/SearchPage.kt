package com.kliachenko.filmoteka.pageobjects.search

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.filmoteka.pageobjects.search.blank.BlankSearchStateUi
import com.kliachenko.filmoteka.pageobjects.search.error.ErrorSearchStateUi
import com.kliachenko.filmoteka.pageobjects.search.history.HistorySearchStateUi
import com.kliachenko.filmoteka.pageobjects.search.initial.InitialSearchStateUi
import com.kliachenko.filmoteka.pageobjects.search.invalid.InvalidSearchStateUi
import com.kliachenko.filmoteka.pageobjects.search.success.SuccessSearchStateUi
import com.kliachenko.filmoteka.pageobjects.search.valid.ValidSearchStateUi
import org.hamcrest.Matcher

class SearchPage {

    private val parentId: Matcher<View> = withParent(withId(com.kliachenko.search.R.id.rootSearchLayout))
    private val parentClass: Matcher<View> = withParent(isAssignableFrom(LinearLayout::class.java))
    private val rootContainerResultId: Int = com.kliachenko.search.R.id.recyclerViewSearch
    private val rootContainerResultClass: Class<RecyclerView> = RecyclerView::class.java
    private val searchViewUi = SearchViewUi(parentId, parentClass)
    private val initialSearchStateUi = InitialSearchStateUi(parentId, parentClass, rootContainerResultId, rootContainerResultClass)
    private val historySearchStateUi = HistorySearchStateUi(parentId, parentClass, rootContainerResultId, rootContainerResultClass)
    private val invalidSearchStateUi = InvalidSearchStateUi(parentId, parentClass, rootContainerResultId, rootContainerResultClass)
    private val validSearchStateUi = ValidSearchStateUi(parentId, parentClass, rootContainerResultId, rootContainerResultClass)
    private val errorSearchStateUi = ErrorSearchStateUi(parentId, parentClass, rootContainerResultId, rootContainerResultClass)
    private val successSearchStateUi = SuccessSearchStateUi(parentId, parentClass, rootContainerResultId, rootContainerResultClass)
    private val blankSearchStateUi = BlankSearchStateUi(parentId, parentClass, rootContainerResultId, rootContainerResultClass)

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

    fun checkInvalidSearchState() {
        initialSearchStateUi.isNotVisible()
        historySearchStateUi.isNotVisible()
        invalidSearchStateUi.checkVisible()
        validSearchStateUi.isNotVisible()
        errorSearchStateUi.isNotVisible()
        successSearchStateUi.isNotVisible()
        blankSearchStateUi.isNotVisible()
    }

    fun checkValidSearchState() {
        initialSearchStateUi.isNotVisible()
        historySearchStateUi.isNotVisible()
        invalidSearchStateUi.isNotVisible()
        validSearchStateUi.checkVisible()
        errorSearchStateUi.isNotVisible()
        successSearchStateUi.isNotVisible()
        blankSearchStateUi.isNotVisible()
    }

    fun checkBlankState() {
        initialSearchStateUi.isNotVisible()
        historySearchStateUi.isNotVisible()
        invalidSearchStateUi.isNotVisible()
        validSearchStateUi.isNotVisible()
        errorSearchStateUi.isNotVisible()
        successSearchStateUi.isNotVisible()
        blankSearchStateUi.checkVisible()
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