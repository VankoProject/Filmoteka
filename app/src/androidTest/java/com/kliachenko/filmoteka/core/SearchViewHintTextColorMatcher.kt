package com.kliachenko.filmoteka.core

import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

class SearchViewHintTextColorMatcher(
    private val hintTextColor: Int,
) : BoundedMatcher<View, SearchView>(SearchView::class.java) {

    override fun describeTo(description: Description) {
        description.appendText("with text color: $hintTextColor")
    }

    override fun matchesSafely(item: SearchView?): Boolean {
        if (item is SearchView) {
            val editText = item.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
            return editText.currentHintTextColor == hintTextColor
        }
        return false
    }
}