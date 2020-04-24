package com.example.idwalltest.data.models

import com.google.gson.annotations.SerializedName

data class FeedResponse(
	@SerializedName("category") val category: String,
	@SerializedName("list") val images: List<String>
)