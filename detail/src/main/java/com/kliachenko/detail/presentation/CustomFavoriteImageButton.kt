package com.kliachenko.detail.presentation

import android.os.Parcel
import android.os.Parcelable
import android.view.View

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