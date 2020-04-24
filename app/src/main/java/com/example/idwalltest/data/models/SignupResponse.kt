package com.example.idwalltest.data.models

import com.google.gson.annotations.SerializedName

data class SignupResponse(
	@SerializedName("user") val user: User
)