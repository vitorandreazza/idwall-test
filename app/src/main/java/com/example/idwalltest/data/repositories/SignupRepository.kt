package com.example.idwalltest.data.repositories

import com.example.idwalltest.data.models.SignupRequest
import com.example.idwalltest.data.models.SignupResponse
import com.example.idwalltest.data.source.remote.SignupService
import retrofit2.Response
import javax.inject.Inject

class SignupRepository @Inject constructor(private val service: SignupService) {

    suspend fun signup(email: String): Response<SignupResponse> =
        service.signup(SignupRequest(email))
}