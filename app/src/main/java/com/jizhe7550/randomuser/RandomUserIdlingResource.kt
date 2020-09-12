package com.jizhe7550.randomuser

import androidx.test.espresso.IdlingResource

interface RadomUserIdlingResource {
    fun getResource(): IdlingResource?
    fun increment()
    fun decrement()
}