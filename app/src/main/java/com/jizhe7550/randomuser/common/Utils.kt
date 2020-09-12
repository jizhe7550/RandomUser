package com.jizhe7550.randomuser.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations

fun <T, R> LiveData<T>.map(transformation: (T) -> R): LiveData<R> =
    Transformations.map(this, transformation)

fun <T, R> LiveData<T>.mapWithDefault(default: R, transformation: (T) -> R): LiveData<R> =
    MediatorLiveData<R>().apply {
        value = default
        addSource(this@mapWithDefault) {
            postValue(transformation(it))
        }
    }
