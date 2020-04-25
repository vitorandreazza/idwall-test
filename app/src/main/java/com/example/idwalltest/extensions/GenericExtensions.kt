package com.example.idwalltest.extensions

import com.example.idwalltest.data.Result

fun <T : Any> T.toResult(): Result<T> =
    if (this is Exception) {
        Result.Error(this)
    } else {
        Result.Success(this)
    }
