package com.example.idwalltest.ui.utils

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.example.idwalltest.R
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

@BindingAdapter(value = ["imgUrl", "errorDrawable"], requireAll = false)
fun ImageView.bindLoadImage(url: String?, @DrawableRes errorDrawable: Int? = null) {
    Picasso.with(context)
        .load(url)
        .error(errorDrawable ?: R.drawable.ic_broken_image)
        .into(this)
}
