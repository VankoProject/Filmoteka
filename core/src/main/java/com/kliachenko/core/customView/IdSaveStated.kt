package com.kliachenko.core.customView

import android.os.Parcel
import android.os.Parcelable
import android.view.View

class IdSavedState : View.BaseSavedState {

    var savedId: Int = 0

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