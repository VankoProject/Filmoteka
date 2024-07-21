package com.kliachenko.dashboard.presentation

import com.kliachenko.core.Communication

interface DashboardCommunication : Communication.Mutable<DashboardUiState> {

    class Base : Communication.Abstract<DashboardUiState>(), DashboardCommunication

}