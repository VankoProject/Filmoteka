package com.kliachenko.search.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import com.kliachenko.core.BaseFragment
import com.kliachenko.core.navigation.NavigationActions
import com.kliachenko.search.databinding.FragmentSearchBinding
import com.kliachenko.search.presentation.adapter.SearchAdapter


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    private lateinit var navigationActions: NavigationActions.FromSearch

    override val viewModelClass: Class<SearchViewModel>
        get() = SearchViewModel::class.java

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentSearchBinding =
        FragmentSearchBinding.inflate(inflater, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is NavigationActions.FromSearch) {
            navigationActions = context
        } else throw RuntimeException("$context must implements NavigationActions")
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SearchAdapter(clickListener = viewModel, navigate = { filmId, filmTitle ->
            navigationActions.navigateFromSearchToDetail(5654, "filmTitle")
            viewModel.clear(viewModelClass)
        })
        binding.searchRecyclerView.adapter = adapter

        viewModel.init()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.search(it)
                    return true
                }
                return false
            }
        })

        binding.searchRecyclerView.setOnTouchListener { _, _ ->
            hideKeyboard(view)
            false
        }

        viewModel.observe(viewLifecycleOwner) { state ->
            state.updateAdapter(adapter)
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}