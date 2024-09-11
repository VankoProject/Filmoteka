package com.kliachenko.core

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

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

    abstract class SingleEvent<T: Any>: Abstract<T>(SingleLiveEvent()) {
        override fun update(value: T) {
            liveData.value = value
        }
    }

}

class SingleLiveEvent<T>: MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner) { t->
            if(mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        }
    }

    @MainThread
    override fun setValue(value: T) {
        mPending.set(true)
        super.setValue(value)
    }
}

interface Observe<T: Any> {
    fun observe(owner: LifecycleOwner, observer: Observer<T>)
}

