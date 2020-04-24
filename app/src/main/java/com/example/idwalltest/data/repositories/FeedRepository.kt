package com.example.idwalltest.data.repositories

import com.example.idwalltest.data.models.FeedCategory
import com.example.idwalltest.data.models.FeedResponse
import com.example.idwalltest.data.source.remote.FeedService
import retrofit2.Response
import javax.inject.Inject

class FeedRepository @Inject constructor(private val service: FeedService) {

    suspend fun getFeed(category: FeedCategory): Response<FeedResponse> = service.getFeed(category)
}