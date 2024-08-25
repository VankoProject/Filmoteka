package com.kliachenko.detail.presentation

import android.view.View
import com.kliachenko.detail.databinding.FragmentDetailBinding

interface DetailUiState {

    fun update(binding: FragmentDetailBinding)

    data class Error(private val errorMessage: String) : DetailUiState {
        override fun update(binding: FragmentDetailBinding) = with(binding) {
            errorState.errorStateLayout.visibility = View.VISIBLE
            progressState.progressDetailLayout.visibility = View.GONE
            successState.successStateLayout.visibility = View.GONE
            errorState.errorDetailTextView.text = errorMessage
        }
    }

    object Progress: DetailUiState {
        override fun update(binding: FragmentDetailBinding) = with(binding){
            errorState.errorStateLayout.visibility = View.GONE
            progressState.progressDetailLayout.visibility = View.VISIBLE
            successState.successStateLayout.visibility = View.GONE
        }
    }

    data class Success(private val detailFilmContent: FilmDetailUi): DetailUiState {
        override fun update(binding: FragmentDetailBinding) = with(binding){
            errorState.errorStateLayout.visibility = View.GONE
            progressState.progressDetailLayout.visibility = View.GONE
            successState.successStateLayout.visibility = View.VISIBLE
            // TODO: continue after creating uiModel
        }

    }
}