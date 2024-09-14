package com.kliachenko.dashboard.presentation.customViews

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kliachenko.dashboard.presentation.DashboardUiState
import com.kliachenko.dashboard.presentation.adapter.DashboardUiType

class CustomRecyclerView : RecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private var bottomScrollListener: TabScrollListener? = null
    private var topScrollListener: TabScrollListener? = null
    private var tabScrollPositions = mutableMapOf<Int, ScrollPosition>()
    private var currentTabPosition: Int = 0
    private val typeList: List<DashboardUiType> = DashboardUiType.typeList()

    init {
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = layoutManager
                if (layoutManager is GridLayoutManager) {
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    val firstVisibleItem =
                        layoutManager.findViewByPosition(firstVisibleItemPosition)
                    val offset = firstVisibleItem?.top ?: 0
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                    if (dy > 0) {
                        bottomScrollListener?.let {
                            tabScrollPositions[currentTabPosition] =
                                ScrollPosition(firstVisibleItemPosition, offset)
                            if (lastVisibleItemPosition >= layoutManager.itemCount - THRESHOLD) {
                                Log.d("Filmoteka", "RV lastVisiblePos: $lastVisibleItemPosition versus itemCount ${layoutManager.itemCount}")
                                it.bottomScrollListener(lastVisibleItemPosition)
                            }
                        }
                    }
                    if(dy < 0) {
                        topScrollListener?.let {
                            tabScrollPositions[currentTabPosition] =
                                ScrollPosition(firstVisibleItemPosition, offset)
                            it.topScrollListener(firstVisibleItemPosition)
                        }
                    }
                }
            }
        })
    }

    fun onBottomScrollListener(listener: TabScrollListener) {
        bottomScrollListener = listener
    }

    fun onTopScrollListener(listener: TabScrollListener) {
        topScrollListener = listener
    }

    fun updateLayoutManager(state: DashboardUiState) {
        layoutManager = when (state) {
            is DashboardUiState.FilmsList -> GridLayoutManager(context, 2)
            else -> LinearLayoutManager(context)
        }
        post {
            (layoutManager as? GridLayoutManager)?.let { gridLayoutManager ->
                val position = tabScrollPositions[currentTabPosition]
                position?.let {
                    gridLayoutManager.scrollToPositionWithOffset(it.position(), it.offset())
                }
                gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        val viewType = adapter?.getItemViewType(position)
                        return if (viewType == typeList.indexOf(DashboardUiType.PagingProgress)) {
                            gridLayoutManager.spanCount
                        } else {
                            1
                        }
                    }
                }
            }
        }
    }

    fun currentTabPosition(tabPosition: Int) {
        currentTabPosition = tabPosition
        layoutManager?.let {
            if (it is GridLayoutManager) {
                val position = tabScrollPositions[tabPosition]
                position?.let { position ->
                    it.scrollToPositionWithOffset(position.position(), position.offset())
                }
            }
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            if (layoutManager is GridLayoutManager) {
                TabScrollState(it).apply {
                    tabPosition = currentTabPosition
                    scrollPositions = tabScrollPositions
                }
            } else {
                it
            }
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoreState = state as TabScrollState?
        super.onRestoreInstanceState(restoreState?.superState)
        tabScrollPositions = restoreState?.scrollPositions?.toMutableMap() ?: mutableMapOf()
        currentTabPosition = restoreState?.tabPosition ?: 0
    }

    fun clearListeners() {
        bottomScrollListener = null
        topScrollListener = null
    }

    companion object {
        private const val THRESHOLD = 1
    }
}

data class ScrollPosition(
    private val position: Int,
    private val offset: Int,
) {
    fun position() = position
    fun offset() = offset
}

class TabScrollState : View.BaseSavedState {

    var scrollPositions: Map<Int, ScrollPosition> = emptyMap()
    var tabPosition: Int = 0

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcel: Parcel) : super(parcel) {
        val size = parcel.readInt()
        val keys = IntArray(size)
        val positions = IntArray(size)
        val offsets = IntArray(size)
        parcel.readIntArray(keys)
        parcel.readIntArray(positions)
        parcel.readIntArray(offsets)
        scrollPositions = keys.zip(positions.zip(offsets).map {
            ScrollPosition(it.first, it.second)
        }).toMap()
        tabPosition = parcel.readInt()
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        val keys = scrollPositions.keys.toIntArray()
        val positions = scrollPositions.values.map { it.position() }.toIntArray()
        val offsets = scrollPositions.values.map { it.offset() }.toIntArray()
        out.writeInt(keys.size)
        out.writeIntArray(keys)
        out.writeIntArray(positions)
        out.writeIntArray(offsets)
        out.writeInt(tabPosition)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<TabScrollState> {
        override fun createFromParcel(source: Parcel) = TabScrollState(source)
        override fun newArray(size: Int): Array<TabScrollState?> = arrayOfNulls(size)
    }
}

interface TabScrollListener {

    fun bottomScrollListener(lastVisibleItemPosition: Int)

    fun topScrollListener(firstVisibleItemPosition: Int)
}