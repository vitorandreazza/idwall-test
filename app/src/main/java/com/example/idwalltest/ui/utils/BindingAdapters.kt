package com.example.idwalltest.ui.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso


@BindingAdapter("visibleIf")
fun View.visibleIf(visible: Boolean) {
    isVisible = visible
}

@BindingAdapter("error")
fun TextInputLayout.error(msg: String?) {
    error = msg
}

@BindingAdapter("error")
fun TextInputLayout.error(@StringRes msg: Int?) {
    error = msg?.let { context.getString(it) }
}

@BindingAdapter("loadImage")
fun ImageView.bindLoadImage(url: String) {
    Picasso.with(context)
        .load(url)
        .into(this)
}
