package com.kliachenko.dashboard.presentation.customViews

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.kliachenko.core.LoadPicEngine
import com.kliachenko.core.ProvideLoadPicEngine

class CustomImageView : AppCompatImageView, ShowPicture {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val picEngine: LoadPicEngine by lazy {
        (context.applicationContext as ProvideLoadPicEngine).picEngine()
    }

    private var imageUrl: String = ""

    override fun show(url: String) {
        picEngine.show(this, url)
        this.imageUrl = url
    }

}

interface ShowPicture {

    fun show(url: String)
}