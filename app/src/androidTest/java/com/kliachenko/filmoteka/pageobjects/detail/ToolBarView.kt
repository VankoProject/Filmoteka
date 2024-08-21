package com.kliachenko.filmoteka.pageobjects.detail

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.kliachenko.filmoteka.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class ToolBarView(parentId: Matcher<View>, parentClass: Matcher<View>) {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val textPlug = uiContext.getText(com.kliachenko.detail.R.string.film_detail_info)
    private val toolBarId: Int = com.kliachenko.detail.R.id.toolBar
    private val interaction: ViewInteraction = onView(
        allOf(
            parentId, parentClass,
            isAssignableFrom(Toolbar::class.java),
            withId(toolBarId)
        )
    )

    fun checkInitial() {
        interaction.apply {
            check(matches(isDisplayed()))
            onView(
                allOf(
                    withParent(withId(toolBarId)),
                    isAssignableFrom(TextView::class.java)
                )
            ).check(matches(withText(textPlug)))
        }
    }

    fun checkSuccess(title: String) {
        interaction.check(matches(isDisplayed()))
        onView(
            allOf(
                withParent(withId(toolBarId)),
                isAssignableFrom(TextView::class.java)
            )
        ).check(matches(withText(title)))

    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun tapBack() {
        onView(withContentDescription(R.string.tool_bar_description))
            .perform(click())
    }

}
