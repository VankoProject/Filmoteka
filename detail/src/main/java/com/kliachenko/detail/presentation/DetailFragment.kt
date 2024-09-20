package com.kliachenko.detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kliachenko.core.BaseFragment
import com.kliachenko.core.SnackBarWrapper
import com.kliachenko.detail.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override val viewModelClass: Class<DetailViewModel>
        get() = DetailViewModel::class.java

    private lateinit var snackBar: SnackBarWrapper

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentDetailBinding.inflate(
            inflater, container, false
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var filmId = 0
        var filmTitle: String? = null

        arguments?.let {
            filmId = it.getInt("filmId")
            filmTitle = it.getString("filmTitle")
        }

        binding.toolBar.title = filmTitle

        snackBar = SnackBarWrapper()

        viewModel.init(filmId = filmId)

        binding.toolBar.setNavigationOnClickListener {
            viewModel.goBack()
        }

        binding.errorState.retryDetailButton.setOnClickListener {
            viewModel.retry()
        }

        binding.successState.favoriteIconId.setOnClickListener {
            viewModel.changeStatus(filmId = filmId) { isFavorite ->
                snackBar.show(binding.root, isFavorite)
            }
        }

        viewModel.observe(viewLifecycleOwner) { state ->
            state.update(binding)
        }

        viewModel.observeNavigation(viewLifecycleOwner) {
            findNavController().popBackStack()
            viewModel.clear(DetailViewModel::class.java)
        }

    }

}