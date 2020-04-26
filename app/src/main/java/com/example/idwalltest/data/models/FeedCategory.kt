package com.example.idwalltest.data.models

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName

enum class FeedCategory {
    @field:SerializedName("husky") HUSKY,
    @field:SerializedName("hound") HOUND,
    @field:SerializedName("pug") PUG,
    @field:SerializedName("labrador") LABRADOR;

    @SuppressLint("DefaultLocale")
    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}