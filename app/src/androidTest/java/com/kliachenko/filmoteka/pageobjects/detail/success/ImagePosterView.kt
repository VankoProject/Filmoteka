package com.kliachenko.filmoteka.pageobjects.detail.success

import android.view.View
import android.widget.ImageView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class ImagePosterView(parentSuccessId: Matcher<View>, parentSuccessClass: Matcher<View>) {

    private val imagePosterId: Int = com.kliachenko.detail.R.id.filmPosterView
    private val interaction: ViewInteraction = onView(
        allOf(
            parentSuccessId, parentSuccessClass,
            isAssignableFrom(ImageView::class.java),
            withId(imagePosterId)
        )
    )

    fun checkSuccess() {
        interaction.apply {
            check(matches(isDisplayed()))
        }
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
