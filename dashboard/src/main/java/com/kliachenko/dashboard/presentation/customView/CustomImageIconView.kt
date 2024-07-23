package com.kliachenko.dashboard.presentation.customView

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.kliachenko.dashboard.R

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

internal class IdSavedState : View.BaseSavedState {

    var savedId: Int = -1

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcel: Parcel) : super(parcel) {
        savedId = parcel.readInt()
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeInt(savedId)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<IdSavedState> {
        override fun createFromParcel(source: Parcel) = IdSavedState(source)
        override fun newArray(size: Int): Array<IdSavedState?> = arrayOfNulls(size)
    }
}

interface SetIcon {
    fun setIcon(isFavorite: Boolean)
}