package com.example.idwalltest.data.models

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName

enum class FeedCategory {
    @SerializedName("husky") HUSKY,
    @SerializedName("hound") HOUND,
    @SerializedName("pug") PUG,
    @SerializedName("labrador") LABRADOR;

    @SuppressLint("DefaultLocale")
    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}