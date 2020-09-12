package com.jizhe7550.randomuser.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.jizhe7550.randomuser.R
import timber.log.Timber

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:loadUrlCircleCrop")
    fun loadUrlCircleCrop(iv: ImageView, url: String?) {
        try {
            Glide.with(iv)
                .load(url ?: "")
                .placeholder(R.drawable.bg_avatar)
                .transform(CircleCrop())
                .into(iv)
        } catch (e: Exception) {
            Timber.e("BindingAdapters.loadUrlCircleCrop ${e.message}")
        }
    }
}