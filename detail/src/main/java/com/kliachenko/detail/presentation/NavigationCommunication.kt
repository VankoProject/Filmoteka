package com.kliachenko.detail.presentation

import com.kliachenko.core.Communication

interface NavigationCommunication : Communication.Mutable<Unit> {

    class Base : Communication.SingleEvent<Unit>(), NavigationCommunication

}