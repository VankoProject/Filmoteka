package com.kliachenko.dashboard.presentation

import com.kliachenko.core.Communication

interface DashboardCommunication: Communication.Abstract<DashboardUiState> {

    class Base: Communication.Mutable<DashboardUiState>, DashboardCommunication {

    }
}