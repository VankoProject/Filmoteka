package com.kliachenko.core

import android.content.Context

interface Core {

    class Base(
        private val context: Context,
    ) : Core {

    }
}