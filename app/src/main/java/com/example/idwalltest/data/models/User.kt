package com.example.idwalltest.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("__v") val V: Int,
    @SerializedName("_id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("token") val token: String,
    @SerializedName("updatedAt") val updatedAt: String
)