package com.kliachenko.search.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kliachenko.search.databinding.ErrorSearchStateBinding
import com.kliachenko.search.databinding.InitialSearchStateBinding
import com.kliachenko.search.databinding.ProgressSearchStateBinding
import com.kliachenko.search.databinding.SuccessSearchStateBinding

class SearchAdapter(
    private val clickListener: ClickActions,
    private val typeList: List<SearchUiType> = SearchUiType.typeList(),
) : RecyclerView.Adapter<SearchViewHolder>(), ShowList {

    private val list = mutableListOf<SearchUi>()

    override fun getItemViewType(position: Int): Int {
        val item = list[position]
        val type = item.type()
        val index = typeList.indexOf(type)
        if (index == -1)
            throw IllegalStateException("Type $type is not included in the typeList $typeList")
        return index
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return typeList[viewType].viewHolder(parent, clickListener)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    override fun show(uiState: List<SearchUi>) {
        val result = SearchDiffUtil(oldList = list, newList = uiState)
        val calculation = DiffUtil.calculateDiff(result)
        list.clear()
        list.addAll(uiState)
        calculation.dispatchUpdatesTo(this)
    }
}

abstract class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: SearchUi) = Unit

    class Initial(private val binding: InitialSearchStateBinding) : SearchViewHolder(binding.root) {
        override fun bind(item: SearchUi) {
            super.bind(item)
        }
    }

    class Error(
        private val binding: ErrorSearchStateBinding,
        private val clickListener: ClickActions,
    ) : SearchViewHolder(binding.root) {
        override fun bind(item: SearchUi) {
            super.bind(item)
            binding.searchRetryButton.setOnClickListener {
                clickListener.retry()
            }
        }
    }

    class Success(private val binding: SuccessSearchStateBinding) : SearchViewHolder(binding.root) {
        override fun bind(item: SearchUi) {
            super.bind(item)
        }
    }

    class Progress(private val binding: ProgressSearchStateBinding) :
        SearchViewHolder(binding.root) {

    }

}

interface ShowList {
    fun show(uiState: List<SearchUi>)
}