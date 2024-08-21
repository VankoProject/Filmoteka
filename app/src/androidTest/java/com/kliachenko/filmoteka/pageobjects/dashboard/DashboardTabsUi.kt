package com.kliachenko.filmoteka.pageobjects.dashboard

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.tabs.TabLayout.TabView
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.filmoteka.core.ColorMatcher
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not

/**
Properties:
1. color //2
2. active position //3
3. clickable
 */

class DashboardTabsUi(
    parentId: Matcher<View>,
    parentClass: Matcher<View>,
    private val tabs: List<String>,
    private val tabsIds: List<Int>,
) {

    private val activeTabColor = "#FFF500"
    private val notActiveTabColor = "#FFFFFF"
    private val interaction: ViewInteraction = onView(
        allOf(
            parentId,
            parentClass,
            withId(com.kliachenko.dashboard.R.id.dashboardTabs),
        )
    )

    fun checkVisible(activeTab: String) {

        interaction.check(matches(isDisplayed()))

        tabs.forEachIndexed { index, tabName ->
            val tabId = tabsIds[index]
            val isClickable = tabName != activeTab
            val colorHex = if (tabName == activeTab) activeTabColor else notActiveTabColor

            onView(
                allOf(
                    withText(tabName),
                    isAssignableFrom(MaterialTextView::class.java),
                    withParent(withId(tabId)),
                    withParent(isAssignableFrom(TabView::class.java)),
                )
            ).apply {
                check(matches(isDisplayed()))
                check(matches(ColorMatcher(colorHex)))
                if (isClickable) {
                    perform(click())
                }
            }
        }
    }

    fun tap(tabName: String) {
        onView(
            allOf(
                withText(tabName),
                isAssignableFrom(MaterialTextView::class.java),
                withParent(isAssignableFrom(TabView::class.java))
            )
        ).perform(click())
    }

    fun checkDashboardIsNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
