package com.kliachenko.detail.presentation

import com.kliachenko.core.Communication

interface DetailCommunication : Communication.Mutable<DetailUiState> {

    class Base: Communication.Abstract<DetailUiState>(), DetailCommunication

}
