package com.kliachenko.filmoteka.core

import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

class SearchViewInputTextColorMatcher(
    private val inputTextColor: Int,
) : BoundedMatcher<View, SearchView>(SearchView::class.java) {
    override fun describeTo(description: Description) {
        description.appendText("with input text color: $inputTextColor")
    }

    override fun matchesSafely(item: SearchView?): Boolean {
        if (item is SearchView) {
            val editText = item.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
            return editText.currentTextColor == inputTextColor
        }
        return false
    }
}