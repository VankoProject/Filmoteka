package com.kliachenko.core.modules

import androidx.lifecycle.ViewModel

interface Clear {

    fun clear(viewModelClass: Class<out ViewModel>)
}