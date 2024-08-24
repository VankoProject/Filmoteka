package com.kliachenko.filmoteka.pageobjects.detail.success.statisticsblockUi.like

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

class LikeIconBackground(likeIconLayoutId: Matcher<View>, likeLayout: Matcher<View>) {

    private val uiContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val likeIconBackgroundId: Matcher<View> = withId(com.kliachenko.detail.R.id.likeIconBackgroundId)
    private val likeBackgroundColor = ContextCompat.getColor(uiContext, com.kliachenko.core.R.color.stillBlue)
    private val unLikeBackgroundColor = ContextCompat.getColor(uiContext, com.kliachenko.core.R.color.liteLimeGreen)
    private val interaction: ViewInteraction = onView(
        allOf(
            likeIconLayoutId, likeLayout,
            likeIconBackgroundId,
            isAssignableFrom(ImageView::class.java)
        )
    )

    fun checkSuccess(status: Boolean) {
        interaction.apply {
            check(matches(isDisplayed()))
            if(status)
                check(matches(ColorMatcher(likeBackgroundColor)))
            else  check(matches(ColorMatcher(unLikeBackgroundColor)))
        }
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
