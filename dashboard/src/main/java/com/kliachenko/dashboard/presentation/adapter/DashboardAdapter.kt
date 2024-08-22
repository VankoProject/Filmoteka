package com.kliachenko.dashboard.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kliachenko.dashboard.databinding.DashboardErrorStateLayoutBinding
import com.kliachenko.dashboard.databinding.DashboardProgressStateLayoutBinding
import com.kliachenko.dashboard.databinding.FilmItemLayoutBinding
import com.kliachenko.dashboard.databinding.NodataLayoutBinding
import com.kliachenko.dashboard.databinding.PagingProgressLayoutBinding

class DashboardAdapter(
    private val clickListener: ClickActions,
    private val navigate: (Int) -> Unit,
    private val typeList: List<DashboardUiType> = DashboardUiType.typeList(),
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
        return typeList[viewType].viewHolder(parent, clickListener, navigate)
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

    class Progress(private val binding: DashboardProgressStateLayoutBinding) :
        DashboardViewHolder(binding.root) {
        override fun bind(item: DashboardUi) {
            super.bind(item)
            item.show(binding)
        }
    }

    class BottomProgress(private val binding: PagingProgressLayoutBinding) :
        DashboardViewHolder(binding.root) {
        override fun bind(item: DashboardUi) {
            super.bind(item)
            item.show(binding)
        }
    }

    class NoData(private val binding: NodataLayoutBinding) : DashboardViewHolder(binding.root) {
        override fun bind(item: DashboardUi) {
            super.bind(item)
            item.show(binding)
        }
    }

    class Error(
        private val binding: DashboardErrorStateLayoutBinding,
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
        private val navigate: (Int) -> Unit,
    ) : DashboardViewHolder(binding.root) {
        override fun bind(item: DashboardUi) {
            item.show(binding)
            binding.root.setOnClickListener {
                if (itemView.id != com.kliachenko.dashboard.R.id.iconImageView) {
                    navigate(item.filmId())
                }
            }
            binding.iconImageView.setOnClickListener {
                val message = if (item.isFavorite()) {
                    clickListener.remove(item)
                    com.kliachenko.core.R.string.removed_from_favorites
                } else {
                    clickListener.add(item)
                    com.kliachenko.core.R.string.added_to_favorites
                }
                Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}


interface ShowList {

    fun show(uiState: List<DashboardUi>)
}