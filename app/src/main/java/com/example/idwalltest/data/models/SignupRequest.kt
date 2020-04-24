package com.example.idwalltest.data.models

import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @SerializedName("email") val email: String
)