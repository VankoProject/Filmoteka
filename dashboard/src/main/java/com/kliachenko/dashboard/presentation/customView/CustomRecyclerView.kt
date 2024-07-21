package com.kliachenko.dashboard.presentation.customView

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kliachenko.dashboard.presentation.DashboardUiState

class CustomRecyclerView: RecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun updateLayoutManager(state: DashboardUiState) {
        layoutManager = when (state) {
            is DashboardUiState.FilmsList -> GridLayoutManager(context, 2)
            else -> LinearLayoutManager(context)
        }
    }
}