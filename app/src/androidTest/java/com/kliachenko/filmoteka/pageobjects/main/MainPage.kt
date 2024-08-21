package com.kliachenko.filmoteka.pageobjects.main

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.filmoteka.R
import org.hamcrest.Matcher

class MainPage {

    private val tabDashboard = "Dashboard"
    private val tabSearch = "Search"
    private val tabFavorites = "Favorites"
    private val parentId: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val parentClass: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val bottomNavigationTabsUi = BottomNavigationTabsUi(
        parentId = parentId,
        parentClass = parentClass,
        tabs = listOf(tabDashboard, tabSearch, tabFavorites),
        tabsIds = listOf(
            R.id.dashboard,
            R.id.search,
            R.id.favorites
        )
    )

    fun checkMainState(activeTab: String) {
        bottomNavigationTabsUi.checkMainState(activeTab = activeTab)
    }

    fun tapNavTab(tabName: String) {
        bottomNavigationTabsUi.tapNavTab(tabName = tabName)
    }

}