package com.kliachenko.filmoteka.core

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

class LinearLayoutChildPositionMatcher(
    private val position: Int,
    private val childMatcher: Matcher<View>
): BoundedMatcher<View, LinearLayout>(LinearLayout::class.java) {

    override fun describeTo(description: Description) {
        description.appendText("has child at position $position that matches: ")
        childMatcher.describeTo(description)
    }

    override fun matchesSafely(item: LinearLayout): Boolean {
        if(position < 0 || position >= item.childCount) return false
        val child = item.getChildAt(position)
        return childMatcher.matches(child)
    }

}