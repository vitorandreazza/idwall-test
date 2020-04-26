package com.example.idwalltest.data.repositories

import com.example.idwalltest.data.Result
import com.example.idwalltest.data.models.FeedCategory
import com.example.idwalltest.data.models.FeedResponse
import com.example.idwalltest.data.source.remote.FeedService
import com.example.idwalltest.utils.safeApiCall
import retrofit2.Response
import javax.inject.Inject

class FeedRepository @Inject constructor(private val service: FeedService) {

    suspend fun getFeed(category: FeedCategory): Result<FeedResponse> = safeApiCall(
        call = { service.getFeed(category) },
        errorMessage = "Error on get feed"
    )
}