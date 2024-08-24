package com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.favorite

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import com.kliachenko.filmoteka.core.ColorMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class FavoriteIconBackground(favoriteIconLayoutId: Matcher<View>, favoriteLayout: Matcher<View>) {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val favoriteIconBackgroundId: Matcher<View> = withId(com.kliachenko.detail.R.id.favoriteIconBackgroundId)
    private val favoriteBackgroundColor = ContextCompat.getColor(uiContext, com.kliachenko.core.R.color.stillBlue)
    private val unFavoriteBackgroundColor = ContextCompat.getColor(uiContext, com.kliachenko.core.R.color.liteLimeGreen)
    private val interaction: ViewInteraction = onView(
        allOf(
            favoriteIconLayoutId, favoriteLayout,
            favoriteIconBackgroundId,
            isAssignableFrom(ImageView::class.java)
        )
    )

    fun checkSuccess(status: Boolean) {
        interaction.apply {
            check(matches(isDisplayed()))
            if(status)
            check(matches(ColorMatcher(favoriteBackgroundColor)))
            else  check(matches(ColorMatcher(unFavoriteBackgroundColor)))
        }
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
