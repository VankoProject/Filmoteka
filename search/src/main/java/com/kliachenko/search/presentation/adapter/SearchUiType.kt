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
        clickActions: ClickActions, navigate: (Int, String) -> Unit,
    ): SearchViewHolder

    object Initial : SearchUiType {
        override fun viewHolder(
            parent: ViewGroup,
            clickActions: ClickActions,
            navigate: (Int, String) -> Unit,
        ) =
            SearchViewHolder.Initial(
                InitialSearchStateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    object Success : SearchUiType {
        override fun viewHolder(
            parent: ViewGroup,
            clickActions: ClickActions,
            navigate: (Int, String) -> Unit,
        ) =
            SearchViewHolder.Success(
                SuccessSearchStateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ), navigate = navigate
            )
    }

    object Error : SearchUiType {
        override fun viewHolder(
            parent: ViewGroup,
            clickActions: ClickActions,
            navigate: (Int, String) -> Unit,
        ) =
            SearchViewHolder.Error(
                ErrorSearchStateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                clickActions
            )
    }

    object Progress : SearchUiType {
        override fun viewHolder(
            parent: ViewGroup,
            clickActions: ClickActions,
            navigate: (Int, String) -> Unit,
        ) =
            SearchViewHolder.Progress(
                ProgressSearchStateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

}
