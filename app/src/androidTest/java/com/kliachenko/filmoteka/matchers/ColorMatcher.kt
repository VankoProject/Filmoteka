package com.kliachenko.filmoteka.matchers

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

class ColorMatcher(
    @ColorRes private val expectedColorId: Int
): BoundedMatcher<View, TextView>(TextView::class.java) {

    constructor(colorString: String) : this(Color.parseColor(colorString))

    override fun describeTo(description: Description) {
        description.appendText("with text color: $expectedColorId")
    }

    override fun matchesSafely(item: TextView): Boolean {
        return item.currentTextColor == expectedColorId
    }
}