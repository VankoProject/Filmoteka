package com.kliachenko.detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
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
        val filmTitle: String = args.filmTitle
        binding.toolBar.title = filmTitle

        viewModel.init(filmId = filmId)

        binding.toolBar.setNavigationOnClickListener {
            viewModel.goBack()
        }

        binding.errorState.retryDetailButton.setOnClickListener {
            viewModel.retry()
        }

        binding.successState.favoriteIconId.setOnClickListener {
            viewModel.changeStatus(filmId = filmId) { isFavorite ->
                val message =
                    if (isFavorite) com.kliachenko.core.R.string.added_to_favorites
                    else com.kliachenko.core.R.string.removed_from_favorites
                Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
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