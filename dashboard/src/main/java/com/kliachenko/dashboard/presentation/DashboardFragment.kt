package com.kliachenko.dashboard.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kliachenko.core.BaseFragment
import com.kliachenko.core.navigation.NavigationActions
import com.kliachenko.dashboard.databinding.FragmentDashboardBinding
import com.kliachenko.dashboard.presentation.adapter.DashboardAdapter

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>(),
    TabSelectedListener, TabScrollListener {

    private lateinit var navigationActions: NavigationActions.FromDashboard

    override val viewModelClass: Class<DashboardViewModel>
        get() = DashboardViewModel::class.java

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentDashboardBinding.inflate(inflater, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is NavigationActions.FromDashboard) {
            navigationActions = context
        } else throw RuntimeException("$context must implements NavigationActions")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = DashboardAdapter(clickListener = viewModel, navigate = { filmId, filmTitle ->
            navigationActions.navigateFromDashboardToDetail(filmId, filmTitle)
            viewModel.clear(viewModelClass)
        })

        with(binding) {
            dashboardRecyclerView.apply {
                this.adapter = adapter
                currentTabPosition(binding.dashboardTabs.selectedTabPosition)
                onBottomScrollListener(this@DashboardFragment)
                onTopScrollListener(this@DashboardFragment)
            }

            dashboardTabs.onTabSelectedListener(this@DashboardFragment)

            viewModel.init(
                firstRun = savedInstanceState == null,
                tabPosition = dashboardTabs.selectedTabPosition
            )

            viewModel.observe(viewLifecycleOwner) { state ->
                (dashboardRecyclerView).updateLayoutManager(state)
                state.updateAdapter(adapter)
            }
        }
    }

    override fun onDestroyView() {
        binding.dashboardRecyclerView.clearListeners()
        binding.dashboardTabs.clearListener()
        super.onDestroyView()
    }

    override fun onTabSelected(position: Int) {
        binding.dashboardRecyclerView.currentTabPosition(position)
        viewModel.loadData(position)
    }

    override fun bottomScrollListener(lastVisibleItemPosition: Int) = with(binding) {
        viewModel.loadMore(lastVisibleItemPosition, dashboardTabs.selectedTabPosition)
    }

    override fun topScrollListener(firstVisibleItemPosition: Int) = Unit

}