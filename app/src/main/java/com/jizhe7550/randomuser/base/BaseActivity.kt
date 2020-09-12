package com.jizhe7550.randomuser.base

import android.transition.Fade
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jizhe7550.randomuser.R

abstract class BaseActivity<E : BaseEvent, VM : BaseViewModel<E>> : AppCompatActivity() {

    abstract val viewModel: VM

    fun enableFadeTransition() {
        val fade = Fade()

        val itemsToExclude = listOf(
            android.R.id.navigationBarBackground,
            android.R.id.statusBarBackground
        )

        itemsToExclude.forEach {
            fade.excludeTarget(it, true)
        }

        fade.excludeTarget(window.decorView.findViewById(R.id.action_bar_container) as View, true)

        window.enterTransition = fade
        window.exitTransition = fade
    }
}