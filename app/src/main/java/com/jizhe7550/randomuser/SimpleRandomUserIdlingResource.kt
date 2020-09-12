package com.jizhe7550.randomuser

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource
import com.jizhe7550.randomuser.RadomUserIdlingResource
import timber.log.Timber

class SimpleRandomUserIdlingResource: RadomUserIdlingResource {

    private val idlingResource = CountingIdlingResource("Remote idling resource", true)

    override fun getResource(): IdlingResource? = idlingResource

    override fun increment() {
        idlingResource.increment()
        Timber.d("Increment")
        idlingResource.dumpStateToLogs()
    }

    override fun decrement() {
        idlingResource.decrement()
        Timber.d("Decrement")
        idlingResource.dumpStateToLogs()
    }

}