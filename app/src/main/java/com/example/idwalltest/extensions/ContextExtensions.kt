package com.example.idwalltest.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat

fun Context.toggleKeyboard(forced: Boolean = false) {
    val imm = ContextCompat.getSystemService(this, InputMethodManager::class.java)
    imm?.toggleSoftInput(
        if (forced) InputMethodManager.SHOW_FORCED else InputMethodManager.SHOW_IMPLICIT,
        0
    )
}

fun Context.shouldShowKeyboard(view: View, show: Boolean) {
    val imm = ContextCompat.getSystemService(this, InputMethodManager::class.java)
    if (show) {
        imm?.showSoftInput(view, 0)
    } else {
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
