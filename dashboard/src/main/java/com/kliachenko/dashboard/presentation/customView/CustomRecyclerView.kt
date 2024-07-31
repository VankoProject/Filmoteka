package com.kliachenko.dashboard.presentation.customView

import android.content.Context
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

    private var onLoadMoreDataListener: (() -> Unit)? = null

    init {
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //todo fix classCastException
                val layoutManager = recyclerView.layoutManager
                layoutManager?.let {
                    val totalItemCount = layoutManager.itemCount
                    val visibleItemCount = layoutManager.childCount
                    val firstVisibleItemPosition = when(it) {
                        is GridLayoutManager -> it.findFirstVisibleItemPosition()
                        is LinearLayoutManager -> it.findFirstVisibleItemPosition()
                        else -> return
                    }
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                    ) {
                        onLoadMoreDataListener?.invoke()
                    }
                }
            }
        })
    }

    fun onLoadMoreDataListener(listener: () -> Unit) {
        this.onLoadMoreDataListener = listener
    }

    fun updateLayoutManager(state: DashboardUiState) {
        //todo fix classCastException
        layoutManager = when (state) {
            is DashboardUiState.FilmsList -> GridLayoutManager(context, 2)
            else -> LinearLayoutManager(context)
        }
    }

}