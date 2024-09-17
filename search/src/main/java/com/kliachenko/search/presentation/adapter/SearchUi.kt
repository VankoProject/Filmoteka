package com.kliachenko.search.presentation.adapter

import android.view.LayoutInflater
import com.google.android.material.textview.MaterialTextView
import com.kliachenko.search.R
import com.kliachenko.search.databinding.BlankSearchStateBinding
import com.kliachenko.search.databinding.ErrorSearchStateBinding
import com.kliachenko.search.databinding.HistorySearchStateBinding
import com.kliachenko.search.databinding.InitialSearchStateBinding
import com.kliachenko.search.databinding.InvalidSearchStateBinding
import com.kliachenko.search.databinding.SuccessSearchStateBinding
import com.kliachenko.search.databinding.ValidSearchStateBinding
import com.kliachenko.search.presentation.customView.CustomImageView

interface SearchUi {

    fun show(binding: HistorySearchStateBinding) = Unit

    fun show(binding: SuccessSearchStateBinding) = Unit

    fun show(binding: ErrorSearchStateBinding) = Unit

    fun show(binding: ValidSearchStateBinding) = Unit

    fun show(binding: InvalidSearchStateBinding) = Unit

    fun show(binding: BlankSearchStateBinding) = Unit

    fun show(binding: InitialSearchStateBinding) = Unit

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
            imageView.show(URL_POSTER + imageUrl)

            binding.filmsContainerLayout.addView(filmItemView)
        }
    }

    data class History(
        private val filmId: Int,
        private val title: String,
        private val imageUrl: String,
    ) : SearchUi {
        override fun type() = SearchUiType.History
        override fun id(): String = javaClass.simpleName
        override fun show(binding: HistorySearchStateBinding) {
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
            imageView.show(URL_POSTER + imageUrl)

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

    object Blank : SearchUi {
        override fun type() = SearchUiType.Blank
        override fun id(): String = javaClass.simpleName
    }

    object Valid : SearchUi {
        override fun type() = SearchUiType.Valid
        override fun id(): String = javaClass.simpleName
    }

    object Invalid : SearchUi {
        override fun type() = SearchUiType.Invalid
        override fun id(): String = javaClass.simpleName
    }

    object Initial : SearchUi {
        override fun type() = SearchUiType.Initial
        override fun id(): String = javaClass.simpleName
    }

    companion object {
        private const val URL_POSTER = "https://image.tmdb.org/t/p/w500"
    }

}
