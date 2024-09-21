package com.kliachenko.filmoteka.core

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

class SearchViewHintTextMatcher(private val hint: String) :
    BoundedMatcher<View, SearchView>(SearchView::class.java) {

    override fun describeTo(description: Description) {
        description.appendText("with hint: $hint")
    }

    override fun matchesSafely(item: SearchView?): Boolean {
        if (item !is SearchView) {
            return false
        }
        val searchViewHint = item.queryHint?.toString()
        return searchViewHint == hint
    }

}