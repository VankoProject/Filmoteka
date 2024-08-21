package com.kliachenko.filmoteka.pageobjects.main

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.kliachenko.filmoteka.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class BottomNavigationTabsUi(
    parentId: Matcher<View>,
    parentClass: Matcher<View>,
    private val tabs: List<String>,
    private val tabsIds: List<Int>,
) {

    private val interaction: ViewInteraction = onView(
        allOf(
            parentId,
            parentClass,
            withId(R.id.bottomNavMenu)
        )
    )

    fun checkMainState(activeTab: String) {
        interaction.check(matches(isDisplayed()))
        tabs.forEachIndexed { index, tabName ->
            val tabId = tabsIds[index]
            val tab = onView(
                allOf(
                    withText(tabName),
                    withId(tabId),
                    isAssignableFrom(BottomNavigationItemView::class.java),
                    isDisplayed()
                )
            )

            tab.check(matches(isDisplayed()))

            if(tabName == activeTab) {
                tab.check(matches(isSelected()))
            } else {
                tab.check(matches(not(isSelected())))
            }

        }
    }

    fun tapNavTab(tabName: String) {
        onView(
            allOf(
                withText(tabName),
                isDescendantOfA(withId(R.id.bottomNavMenu)),
                isDisplayed()
            )
        ).perform(click())
    }

}
