package com.kliachenko.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kliachenko.core.BaseFragment
import com.kliachenko.search.databinding.FragmentSearchBinding


class SearchFragment: BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    override val viewModelClass: Class<SearchViewModel>
        get() = TODO("Not yet implemented")


    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentSearchBinding =
        FragmentSearchBinding.inflate(inflater, container, false)



}