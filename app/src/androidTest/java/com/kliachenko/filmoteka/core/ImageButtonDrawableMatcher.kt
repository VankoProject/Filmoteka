package com.kliachenko.filmoteka.core

import android.view.View
import android.widget.ImageButton
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class ImageButtonDrawableMatcher(@DrawableRes private val id: Int) : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description) {
        description.appendText("ImageButton with drawable same as drawable with id $id")
    }

    override fun matchesSafely(item: View): Boolean {
        val expectedDrawable = ContextCompat.getDrawable(item.context, id)!!.toBitmap()
        val actualDrawable = (item as ImageButton).drawable.toBitmap()
        return expectedDrawable.sameAs(actualDrawable)
    }
}