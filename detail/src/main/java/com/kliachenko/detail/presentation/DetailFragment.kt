package com.kliachenko.detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kliachenko.core.BaseFragment
import com.kliachenko.detail.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override val viewModelClass: Class<DetailViewModel>
        get() = DetailViewModel::class.java

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentDetailBinding.inflate(
            inflater, container, false
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = DetailFragmentArgs.fromBundle(requireArguments())
        val filmId = args.filmId
        val filmTitle = args.filmTitle

        binding.toolBar.title = filmTitle

        viewModel.init(filmId = filmId)

        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.errorState.retryDetailButton.setOnClickListener {
            viewModel.retry()
        }

        binding.successState.favoriteIconId.setOnClickListener {
            viewModel.changeStatus(filmId = filmId)
        }

    }

}