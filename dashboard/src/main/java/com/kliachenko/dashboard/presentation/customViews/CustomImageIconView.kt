package com.kliachenko.dashboard.presentation.customViews

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.kliachenko.core.R

class CustomImageIconView : AppCompatImageView, SetIcon {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private var currentIconResId: Int = 0

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            IdSavedState(it).apply {
                savedId = currentIconResId
            }
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoreState = state as IdSavedState?
        super.onRestoreInstanceState(restoreState?.superState)
        currentIconResId = state?.savedId ?: 0
        setImageResource(currentIconResId)
    }

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
        currentIconResId = resId
    }

    override fun setIcon(isFavorite: Boolean) {
        currentIconResId =
            if (isFavorite) R.drawable.ic_like_bookmark
            else R.drawable.ic_unlike_bookmark
        setImageResource(currentIconResId)
    }
}

interface SetIcon {
    fun setIcon(isFavorite: Boolean)
}