package com.kliachenko.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kliachenko.search.databinding.BlankSearchStateBinding
import com.kliachenko.search.databinding.ErrorSearchStateBinding
import com.kliachenko.search.databinding.HistorySearchStateBinding
import com.kliachenko.search.databinding.InitialSearchStateBinding
import com.kliachenko.search.databinding.InvalidSearchStateBinding
import com.kliachenko.search.databinding.SuccessSearchStateBinding
import com.kliachenko.search.databinding.ValidSearchStateBinding

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

    object History : SearchUiType {
        override fun viewHolder(parent: ViewGroup, clickActions: ClickActions) =
            SearchViewHolder.History(
                HistorySearchStateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    object Valid : SearchUiType {
        override fun viewHolder(parent: ViewGroup, clickActions: ClickActions) =
            SearchViewHolder.Valid(
                ValidSearchStateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    object Invalid : SearchUiType {
        override fun viewHolder(parent: ViewGroup, clickActions: ClickActions) =
            SearchViewHolder.Invalid(
                InvalidSearchStateBinding.inflate(
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

    object Blank : SearchUiType {
        override fun viewHolder(parent: ViewGroup, clickActions: ClickActions) =
            SearchViewHolder.Blank(
                BlankSearchStateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    companion object {
        fun typeList(): List<SearchUiType> {
            return listOf(
                Initial,
                History,
                Error,
                Valid,
                Invalid,
                Success,
                Blank
            )
        }
    }


}
