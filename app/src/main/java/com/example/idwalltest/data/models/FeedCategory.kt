package com.example.idwalltest.data.models

import com.google.gson.annotations.SerializedName

enum class FeedCategory {
    @SerializedName("husky") HUSKY,
    @SerializedName("hound") HOUND,
    @SerializedName("pug") PUG,
    @SerializedName("labrador") LABRADOR
}