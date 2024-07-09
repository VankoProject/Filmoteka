package com.kliachenko.dashboard.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.kliachenko.core.BaseFragment
import com.kliachenko.dashboard.databinding.FragmentDashboardBinding

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    override val viewModelClass: Class<DashboardViewModel>
        get() = DashboardViewModel::class.java

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentDashboardBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init(firstRun = savedInstanceState == null)

        binding.dashboardTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.chooseCategory(tabId = tab.id)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })


    }

}