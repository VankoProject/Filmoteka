package com.kliachenko.search.presentation.adapter

import android.view.LayoutInflater
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.search.R
import com.kliachenko.search.databinding.ErrorSearchStateBinding
import com.kliachenko.search.databinding.InitialSearchStateBinding
import com.kliachenko.search.databinding.ProgressSearchStateBinding
import com.kliachenko.search.databinding.SuccessSearchStateBinding
import com.kliachenko.search.presentation.customView.CustomImageView

interface SearchUi {

    fun show(binding: SuccessSearchStateBinding) = Unit

    fun show(binding: ErrorSearchStateBinding) = Unit

    fun show(binding: InitialSearchStateBinding) = Unit

    fun show(binding: ProgressSearchStateBinding) = Unit

    fun type(): SearchUiType

    fun id(): String

    fun filmId(): Int = -1

    data class Success(
        private val filmId: Int,
        private val title: String,
        private val imageUrl: String,
    ) : SearchUi {
        override fun type() = SearchUiType.Success
        override fun id(): String = javaClass.simpleName
        override fun filmId() = filmId
        override fun show(binding: SuccessSearchStateBinding) {
            binding.filmsContainerLayout.removeAllViews()
            val inflater = LayoutInflater.from(binding.root.context)
            val filmItemView =
                inflater.inflate(
                    R.layout.search_film_item_layout,
                    binding.filmsContainerLayout,
                    false
                )
            val filmTitle = filmItemView.findViewById<MaterialTextView>(R.id.searchTitleTextView)
            val imageView = filmItemView.findViewById<CustomImageView>(R.id.searchItemImageView)

            filmTitle.text = title
            imageView.show(imageUrl)

            binding.filmsContainerLayout.addView(filmItemView)
        }
    }

    data class Error(
        private val errorMessage: String,
    ) : SearchUi {
        override fun type() = SearchUiType.Error
        override fun id(): String = javaClass.simpleName
        override fun show(binding: ErrorSearchStateBinding) {
            binding.searchErrorTextView.text = errorMessage
        }
    }

    data class Progress(
        private val progressMessage: String,
    ) : SearchUi {
        override fun type() = SearchUiType.Progress
        override fun id(): String = javaClass.simpleName
        override fun show(binding: ProgressSearchStateBinding) {
            binding.progressTextView.text = progressMessage
        }
    }

    data class Initial(
        private val titleMessage: String,
    ) : SearchUi {
        override fun type() = SearchUiType.Initial
        override fun id(): String = javaClass.simpleName
        override fun show(binding: InitialSearchStateBinding) {
            binding.initialSearchTextView.text = titleMessage
        }
    }

}
