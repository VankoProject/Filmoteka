package com.kliachenko.dashboard.presentation.customView

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
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

    private var currentScrollPosition: Int = 0
    private var onLoadMoreDataListener: (() -> Unit)? = null
    private var onLoadPreviousDataListener: (() -> Unit)? = null
    private var isGridMode = false

    init {
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isGridMode) {
                    val layoutManager = recyclerView.layoutManager
                    if (layoutManager is GridLayoutManager) {
                        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                        val totalItemCount = layoutManager.itemCount
                        val visibleItemCount = layoutManager.childCount
                        if (dy > 0) {
                            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                                onLoadMoreDataListener?.invoke()
                            }
                        } else if (dy < 0) {
                            if (firstVisibleItemPosition == 0 && visibleItemCount > 0) {
                                onLoadPreviousDataListener?.invoke()
                            }
                        }
                        currentScrollPosition = firstVisibleItemPosition
                    }
                }
            }
        })
    }

    fun onLoadMoreDataListener(listener: () -> Unit) {
        this.onLoadMoreDataListener = listener
    }

    fun onLoadPreviousDataListener(listener: () -> Unit) {
        this.onLoadPreviousDataListener = listener
    }

    fun updateLayoutManager(state: DashboardUiState) {
        layoutManager = when (state) {
            is DashboardUiState.FilmsList -> {
                isGridMode = true
                GridLayoutManager(context, 2)
            }

            else -> {
                isGridMode = false
                LinearLayoutManager(context)
            }
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        val layoutManager = layoutManager
        return if (superState != null && isGridMode && layoutManager is GridLayoutManager) {
            IdSavedState(superState).apply {
                savedId = layoutManager.findFirstVisibleItemPosition()
            }
        } else {
            superState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoreState = state as IdSavedState?
        super.onRestoreInstanceState(restoreState?.superState)
        currentScrollPosition = restoreState?.savedId ?: 0
        (layoutManager as? GridLayoutManager)?.scrollToPosition(currentScrollPosition)
    }

}