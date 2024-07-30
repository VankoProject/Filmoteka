package com.kliachenko.dashboard.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kliachenko.core.BaseFragment
import com.kliachenko.dashboard.databinding.FragmentDashboardBinding
import com.kliachenko.dashboard.presentation.adapter.DashboardAdapter
import com.kliachenko.dashboard.presentation.customView.TabSelectedListener

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>(),
    TabSelectedListener {

    override val viewModelClass: Class<DashboardViewModel>
        get() = DashboardViewModel::class.java

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentDashboardBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DashboardAdapter(clickListener = viewModel, navigate = { filmId ->
            findNavController().navigate(
                DashboardFragmentDirections.actionDashBoardFragmentToDetailFragment(
                    filmId
                )
            )
            viewModel.clear(viewModelClass)
        })

        binding.dashboardRecyclerView.apply {
            this.adapter = adapter
            onLoadMoreDataListener {
                viewModel.loadMore()
            }
        }

        binding.dashboardTabs.setOnTabSelectedListener(this)

        viewModel.init(
            firstRun = savedInstanceState == null,
            tabPosition = binding.dashboardTabs.selectedTabPosition
        )

        viewModel.observe(viewLifecycleOwner) { state ->
            state.updateAdapter(adapter)
            (binding.dashboardRecyclerView).updateLayoutManager(state)
        }
    }

    override fun onTabSelected(position: Int) {
        viewModel.load(position)
    }


}