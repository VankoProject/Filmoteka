package com.kliachenko.dashboard.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kliachenko.dashboard.databinding.ErrorLayoutBinding
import com.kliachenko.dashboard.databinding.FilmItemLayoutBinding
import com.kliachenko.dashboard.databinding.ProgressLayoutBinding

class DashboardAdapter(
    private val clickListener: ClickActions,
    private val typeList: List<DashboardUiType> = listOf(DashboardUiType.Error, DashboardUiType.Progress, DashboardUiType.Film),
) : RecyclerView.Adapter<DashboardViewHolder>(), ShowList {

    private val list = mutableListOf<DashboardUi>()

    override fun getItemViewType(position: Int): Int {
        val item = list[position]
        val type = item.type()
        val index = typeList.indexOf(type)
        if (index == -1)
            throw IllegalStateException("Type $type is not included in the typeList $typeList")
        return index
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        return typeList[viewType].viewHolder(parent, clickListener)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    override fun show(uiState: List<DashboardUi>) {
        val result = DashboardDiffUtil(oldList = list, newList = uiState)
        val calculation = DiffUtil.calculateDiff(result)
        list.clear()
        list.addAll(uiState)
        calculation.dispatchUpdatesTo(this)
    }
}

abstract class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: DashboardUi) = Unit

    class Progress(private val binding: ProgressLayoutBinding) :
        DashboardViewHolder(binding.root) {
        override fun bind(item: DashboardUi) {
            super.bind(item)
            item.show(binding)
        }
    }

    class Error(
        private val binding: ErrorLayoutBinding,
        private val clickActions: ClickActions,
    ) : DashboardViewHolder(binding.root) {
        override fun bind(item: DashboardUi) {
            item.show(binding)
            binding.retryButton.setOnClickListener {
                clickActions.retry()
            }
        }
    }

    class Film(
        private val binding: FilmItemLayoutBinding,
        private val clickListener: ClickActions,
    ) : DashboardViewHolder(binding.root) {
        override fun bind(item: DashboardUi) {
            item.show(binding)
            binding.posterImageView.setOnClickListener {
                clickListener.openDetail()
            }
            binding.iconImageView.setOnClickListener {
                clickListener.changeStatus(item = item)
            }
        }
    }
}

interface ShowList {
    fun show(list: List<DashboardUi>)
}