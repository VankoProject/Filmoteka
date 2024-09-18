package com.kliachenko.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kliachenko.search.databinding.ErrorSearchStateBinding
import com.kliachenko.search.databinding.InitialSearchStateBinding
import com.kliachenko.search.databinding.ProgressSearchStateBinding
import com.kliachenko.search.databinding.SuccessSearchStateBinding

interface SearchUiType {

    fun viewHolder(
        parent: ViewGroup,
        clickActions: ClickActions,
    ): SearchViewHolder

    object Initial : SearchUiType {
        override fun viewHolder(parent: ViewGroup, clickActions: ClickActions) =
            SearchViewHolder.Initial(
                InitialSearchStateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    object Success : SearchUiType {
        override fun viewHolder(parent: ViewGroup, clickActions: ClickActions) =
            SearchViewHolder.Success(
                SuccessSearchStateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    object Error : SearchUiType {
        override fun viewHolder(parent: ViewGroup, clickActions: ClickActions) =
            SearchViewHolder.Error(
                ErrorSearchStateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                clickActions
            )
    }

    object Progress : SearchUiType {
        override fun viewHolder(parent: ViewGroup, clickActions: ClickActions) =
            SearchViewHolder.Progress(
                ProgressSearchStateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    companion object {
        fun typeList(): List<SearchUiType> {
            return listOf(
                Initial,
                Error,
                Success,
                Progress
            )
        }
    }


}
