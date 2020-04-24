package com.example.idwalltest.data.source.remote

import com.example.idwalltest.data.models.SignupRequest
import com.example.idwalltest.data.models.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupService {

    @POST("/signup")
    suspend fun signup(@Body signup: SignupRequest): Response<SignupResponse>
}