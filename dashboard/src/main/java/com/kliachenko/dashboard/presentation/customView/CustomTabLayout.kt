package com.kliachenko.dashboard.presentation.customView

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout

class CustomTabLayout : TabLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAtt: Int) : super(
        context,
        attrs,
        defStyleAtt
    )

    private var currentTabPosition: Int = 0
    private var isRestore: Boolean = false
    private var tabSelectedListener: TabSelectedListener? = null

    init {
        addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: Tab?) {
                tab?.let {
                    if(!isRestore) {
                        tabSelectedListener?.onTabSelected(it.position)
                    }
                    currentTabPosition = it.position
                }
            }

            override fun onTabUnselected(tab: Tab?) = Unit
            override fun onTabReselected(tab: Tab?) = Unit
        })
    }

    fun onTabSelectedListener(listener: TabSelectedListener) {
        tabSelectedListener = listener
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            IdSavedState(it).apply {
                savedId = currentTabPosition
            }
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        isRestore = true
        val restoreState = state as IdSavedState?
        super.onRestoreInstanceState(restoreState?.superState)
        currentTabPosition = restoreState?.savedId?: 0
        getTabAt(currentTabPosition)?.select()
        isRestore = false
    }
}

interface TabSelectedListener {

    fun onTabSelected(position: Int)

}