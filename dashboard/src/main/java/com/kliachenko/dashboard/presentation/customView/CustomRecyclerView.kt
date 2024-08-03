package com.kliachenko.dashboard.presentation.customView

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kliachenko.dashboard.presentation.DashboardUiState

class CustomRecyclerView : RecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private var tabScrollListener: TabScrollListener? = null

    init {
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = layoutManager
                if (layoutManager is GridLayoutManager) {
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                    tabScrollListener?.onTabScrollListener(lastVisibleItemPosition)
                    Log.d("Filmateka", "onScrolled $lastVisibleItemPosition")
                }
            }
        })
    }

    fun onTabScrollListener(listener: TabScrollListener) {
        tabScrollListener = listener
    }

    fun updateLayoutManager(state: DashboardUiState) {
        layoutManager = when (state) {
            is DashboardUiState.FilmsList -> {
                GridLayoutManager(context, 2)
            }

            else -> {
                LinearLayoutManager(context)
            }
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        Log.d("Filmateka", "onSaveInstanceState Recycler")
        val superState = super.onSaveInstanceState()
        val layoutManager = layoutManager
        return if (superState != null && layoutManager is GridLayoutManager) {
            IdSavedState(superState).apply {
                savedId = layoutManager.findFirstVisibleItemPosition()
                Log.d("Filmateka", "savedId $savedId")
            }
        } else {
            superState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoreState = state as IdSavedState?
        super.onRestoreInstanceState(restoreState?.superState)
        post {
            val layoutManager = layoutManager
            if (layoutManager is GridLayoutManager) {
                val currentScrollPosition = restoreState?.savedId ?: 0
                Log.d("Filmateka", "currentScrollPosition $currentScrollPosition")
                layoutManager.scrollToPosition(currentScrollPosition)
                Log.d("Filmateka", "layoutManager $currentScrollPosition")
            }
        }
    }

}

interface TabScrollListener {

    fun onTabScrollListener(lastVisibleItemPosition: Int)
}