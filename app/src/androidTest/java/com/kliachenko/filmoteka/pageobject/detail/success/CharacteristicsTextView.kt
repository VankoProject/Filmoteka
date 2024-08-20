package com.kliachenko.filmoteka.pageobject.detail.success

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.core.CharacteristicsTextMatcher
import com.kliachenko.filmoteka.core.ColorMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class CharacteristicsTextView(parentSuccessId: Matcher<View>, parentSuccessClass: Matcher<View>) {

    private val characteristicsId: Int = R.id.characteristicsTextView
    private val textColor = "#FFFFFF"
    private val interaction: ViewInteraction = onView(
        allOf(
            parentSuccessId, parentSuccessClass,
            isAssignableFrom(MaterialTextView::class.java),
            withId(characteristicsId)
        )
    )

    fun checkSuccess(genres: List<String>, releaseDate: String, runtime: String, adult: Boolean) {
        interaction.apply {
            check(matches(isDisplayed()))
            check(matches(ColorMatcher(textColor)))
            check(matches(CharacteristicsTextMatcher(genres, releaseDate, runtime, adult)))
        }
    }

    fun isNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}
