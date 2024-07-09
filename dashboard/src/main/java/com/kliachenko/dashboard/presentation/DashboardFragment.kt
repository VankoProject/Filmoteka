package com.kliachenko.dashboard.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kliachenko.core.BaseFragment
import com.kliachenko.dashboard.databinding.FragmentDashboardBinding

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    override val viewModelClass: Class<DashboardViewModel>
        get() = DashboardViewModel::class.java

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentDashboardBinding.inflate(inflater, container, false)

}