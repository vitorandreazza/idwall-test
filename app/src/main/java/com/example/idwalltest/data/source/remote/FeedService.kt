package com.example.idwalltest.data.source.remote

import com.example.idwalltest.data.models.FeedCategory
import com.example.idwalltest.data.models.FeedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedService {

    @GET("/feed")
    suspend fun getFeed(@Query("category") category: FeedCategory): Response<FeedResponse>
}