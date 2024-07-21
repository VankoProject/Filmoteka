package com.kliachenko.dashboard.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.kliachenko.core.BaseFragment
import com.kliachenko.dashboard.databinding.FragmentDashboardBinding
import com.kliachenko.dashboard.presentation.adapter.DashboardAdapter

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    override val viewModelClass: Class<DashboardViewModel>
        get() = DashboardViewModel::class.java

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentDashboardBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DashboardAdapter(clickListener = viewModel)
        binding.dashboardRecyclerView.adapter = adapter

        viewModel.init(
            firstRun = savedInstanceState == null,
            tabPosition = binding.dashboardTabs.selectedTabPosition
        )

        viewModel.liveData().observe(viewLifecycleOwner) {
            it.updateAdapter(adapter)
        }

        binding.dashboardTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                viewModel.load(position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val restoredTabPosition = savedInstanceState?.getInt("tabPosition") ?: 0
        binding.dashboardTabs.getTabAt(restoredTabPosition)?.select()
        viewModel.load(restoredTabPosition)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("tabPosition", binding.dashboardTabs.selectedTabPosition)
    }

}