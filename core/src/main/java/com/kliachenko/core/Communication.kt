package com.kliachenko.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Communication {

    interface Read<T : Any> {
        fun liveData(): LiveData<T>
    }

    interface Update<T : Any> {
        fun update(value: T)
    }

    interface Mutable<T: Any> : Read<T>, Update<T>, Observe<T>

    abstract class Abstract<T: Any>(
        protected val liveData: MutableLiveData<T> = MutableLiveData(),
    ): Mutable<T> {

        override fun liveData(): LiveData<T> {
            return liveData
        }

        override fun update(value: T) {
            liveData.value = value
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }
    }
}

interface Observe<T: Any> {
    fun observe(owner: LifecycleOwner, observer: Observer<T>)
}

