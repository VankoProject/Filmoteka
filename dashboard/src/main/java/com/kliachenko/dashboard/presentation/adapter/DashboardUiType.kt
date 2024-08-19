package com.kliachenko.dashboard.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kliachenko.dashboard.databinding.ErrorLayoutBinding
import com.kliachenko.dashboard.databinding.FilmItemLayoutBinding
import com.kliachenko.dashboard.databinding.NodataLayoutBinding
import com.kliachenko.dashboard.databinding.PagingProgressLayoutBinding
import com.kliachenko.dashboard.databinding.ProgressLayoutBinding

interface DashboardUiType {

    fun viewHolder(
        parent: ViewGroup,
        clickActions: ClickActions,
        navigate: (Int) -> Unit,
    ): DashboardViewHolder

    object Progress : DashboardUiType {
        override fun viewHolder(
            parent: ViewGroup,
            clickActions: ClickActions,
            navigate: (Int) -> Unit,
        ) = DashboardViewHolder.Progress(
            ProgressLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    object PagingProgress : DashboardUiType {
        override fun viewHolder(
            parent: ViewGroup,
            clickActions: ClickActions,
            navigate: (Int) -> Unit,
        ) = DashboardViewHolder.BottomProgress(
            PagingProgressLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    object Error : DashboardUiType {
        override fun viewHolder(
            parent: ViewGroup,
            clickActions: ClickActions,
            navigate: (Int) -> Unit,
        ) = DashboardViewHolder.Error(
            ErrorLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), clickActions
        )
    }

    object Film : DashboardUiType {
        override fun viewHolder(
            parent: ViewGroup,
            clickActions: ClickActions,
            navigate: (Int) -> Unit,
        ) = DashboardViewHolder.Film(
            FilmItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), clickActions, navigate
        )
    }

    object NoData : DashboardUiType {
        override fun viewHolder(
            parent: ViewGroup,
            clickActions: ClickActions,
            navigate: (Int) -> Unit,
        ) = DashboardViewHolder.NoData(
            NodataLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    companion object {
        fun typeList(): List<DashboardUiType> {
            return listOf(
                Progress,
                PagingProgress,
                Error,
                Film,
                NoData
            )
        }
    }

}
