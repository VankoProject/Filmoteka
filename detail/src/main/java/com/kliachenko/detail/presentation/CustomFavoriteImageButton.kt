package com.kliachenko.detail.presentation

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat

class CustomFavoriteImageButton : AppCompatImageButton {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private var backgroundTintResId: Int = -1

    fun favoriteStatus(isFavorite: Boolean) {
        backgroundTintResId =
            if (isFavorite) com.kliachenko.core.R.color.liteLimeGreen
            else com.kliachenko.core.R.color.stillBlue
        if (backgroundTintResId != -1)
            backgroundTintList = ContextCompat.getColorStateList(context, backgroundTintResId)
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            BackgroundColorState(it).apply {
                savedColorId = backgroundTintResId
            }
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoreState = state as BackgroundColorState?
        super.onRestoreInstanceState(restoreState?.superState)
        restoreState?.let {
            backgroundTintResId = it.savedColorId
            backgroundTintList = ContextCompat.getColorStateList(context, backgroundTintResId)
        }
    }
}

private class BackgroundColorState : View.BaseSavedState {

    var savedColorId: Int = -1

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcel: Parcel) : super(parcel) {
        savedColorId = parcel.readInt()
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeInt(savedColorId)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<BackgroundColorState> {
        override fun createFromParcel(source: Parcel) = BackgroundColorState(source)
        override fun newArray(size: Int): Array<BackgroundColorState?> = arrayOfNulls(size)
    }
}