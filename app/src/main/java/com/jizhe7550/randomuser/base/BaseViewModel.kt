package com.jizhe7550.randomuser.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jizhe7550.randomuser.common.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.jetbrains.annotations.TestOnly
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseViewModel<T : BaseEvent> : ViewModel(), KoinComponent {

    private val _events = SingleLiveEvent<T>()
    val events: LiveData<T>
        get() = _events

    protected fun postEvent(event: T?, sync: Boolean = false) {
        if (sync) {
            _events.value = event
        } else {
            _events.postValue(event)
        }
    }

    @TestOnly
    fun getViewModelScope() = viewModelScope
}
