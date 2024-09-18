package com.kliachenko.filmoteka.pageobjects.search

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.filmoteka.pageobjects.search.error.ErrorSearchStateUi
import com.kliachenko.filmoteka.pageobjects.search.initial.InitialSearchStateUi
import com.kliachenko.filmoteka.pageobjects.search.progress.ProgressSearchStateUi
import com.kliachenko.filmoteka.pageobjects.search.success.SuccessSearchStateUi
import org.hamcrest.Matcher

class SearchPage {

    private val parentId: Matcher<View> = withParent(withId(com.kliachenko.search.R.id.rootSearchLayout))
    private val parentClass: Matcher<View> = withParent(isAssignableFrom(LinearLayout::class.java))
    private val rootContainerResultId: Int = com.kliachenko.search.R.id.recyclerViewSearch
    private val rootContainerResultClass: Class<RecyclerView> = RecyclerView::class.java
    private val searchViewUi = SearchViewUi(parentId, parentClass)
    private val initialSearchStateUi = InitialSearchStateUi(parentId, parentClass, rootContainerResultId, rootContainerResultClass)
    private val progressSearchStateUi = ProgressSearchStateUi(parentId, parentClass, rootContainerResultId, rootContainerResultClass)
    private val errorSearchStateUi = ErrorSearchStateUi(parentId, parentClass, rootContainerResultId, rootContainerResultClass)
    private val successSearchStateUi = SuccessSearchStateUi(parentId, parentClass, rootContainerResultId, rootContainerResultClass)

    fun checkInitialState(titleMessage: String) {
        searchViewUi.checkInitial()
        initialSearchStateUi.checkVisible(titleMessage = titleMessage)
        progressSearchStateUi.isNotVisible()
        errorSearchStateUi.isNotVisible()
        successSearchStateUi.isNotVisible()
    }

    fun type(text: String) {
        searchViewUi.type(text = text)
    }

    fun tapClearAllInput() {
        searchViewUi.tapClearAllInput()
    }

    fun checkErrorState(errorMessage: String) {
        initialSearchStateUi.isNotVisible()
        progressSearchStateUi.isNotVisible()
        errorSearchStateUi.checkVisible(errorMessage = errorMessage)
        successSearchStateUi.isNotVisible()
    }

    fun retry() {
        errorSearchStateUi.retry()
    }

    fun checkSuccessfulState(subscriptionText: String, filmSearchItems: List<FilmSearchItem>) {
        initialSearchStateUi.isNotVisible()
        progressSearchStateUi.isNotVisible()
        errorSearchStateUi.isNotVisible()
        successSearchStateUi.checkVisible(subscriptionText = subscriptionText, films = filmSearchItems)
    }

    fun checkProgressState(progressMessage: String) {
        initialSearchStateUi.isNotVisible()
        progressSearchStateUi.checkVisible(progressMessage = progressMessage)
        errorSearchStateUi.isNotVisible()
        successSearchStateUi.isNotVisible()
    }

    fun tapFilm(position: Int) {
        successSearchStateUi.tapFilm(position = position)
    }
}