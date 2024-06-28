package com.kliachenko.filmoteka.pageobject.main

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.kliachenko.filmoteka.R
import org.hamcrest.Matcher

class MainPage {

    private val parentId: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val parentClass: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val bottomNavigationTabsUi = BottomNavigationTabsUi(
        parentId = parentId,
        parentClass = parentClass,
        tabs = listOf("Dashboard", "Search", "Favorites"),
        tabsIds = listOf(R.id.dashboardTab, R.id.searchTab, R.id.favorites)
    )

    fun checkMainState(activeTab: String) {
        bottomNavigationTabsUi.checkMainState(activeTab = activeTab)
    }

    fun tapNavTab(tabName: String) {
        bottomNavigationTabsUi.tapNavTab(tabName = tabName)
    }

}