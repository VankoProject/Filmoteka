package com.kliachenko.detail.presentation.customViews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View.BaseSavedState
import androidx.appcompat.widget.AppCompatImageView
import com.kliachenko.core.ChangeStatus
import com.kliachenko.detail.R

class CustomIconBackgroundImageView : AppCompatImageView, ChangeStatus {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var backgroundTint: Int = Color.TRANSPARENT
    private val favoriteColor by lazy { ColorStateList.valueOf(context.getColor(com.kliachenko.core.R.color.liteLimeGreen)) }
    private val unFavoriteColor by lazy { ColorStateList.valueOf(context.getColor(com.kliachenko.core.R.color.stillBlue)) }

    init {
        setBackgroundResource(R.drawable.circle_border_shape_icon)
    }

    override fun changeStatus(isFavorite: Boolean) {
        backgroundTint = if (isFavorite) favoriteColor.defaultColor else unFavoriteColor.defaultColor
        backgroundTintList = ColorStateList.valueOf(backgroundTint)
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        return ColorSavedState(superState).apply {
            savedTintColor = backgroundTint
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoreState = state as ColorSavedState?
        super.onRestoreInstanceState(restoreState?.superState)
        backgroundTint = restoreState?.savedTintColor ?: unFavoriteColor.defaultColor
        backgroundTintList = ColorStateList.valueOf(backgroundTint)
    }
}

private class ColorSavedState : BaseSavedState {

    var savedTintColor: Int = Color.TRANSPARENT

    constructor(parcel: Parcel) : super(parcel) {
        savedTintColor = parcel.readInt()
    }

    constructor(superState: Parcelable?) : super(superState)

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeInt(savedTintColor)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ColorSavedState> {
        override fun createFromParcel(source: Parcel) = ColorSavedState(source)
        override fun newArray(size: Int): Array<ColorSavedState?> = arrayOfNulls(size)
    }

}