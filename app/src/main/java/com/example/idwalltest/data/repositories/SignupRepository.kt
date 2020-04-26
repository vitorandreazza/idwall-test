package com.example.idwalltest.data.repositories

import com.example.idwalltest.data.Result
import com.example.idwalltest.data.models.SignupRequest
import com.example.idwalltest.data.models.SignupResponse
import com.example.idwalltest.data.source.local.DefaultSharedPreferences
import com.example.idwalltest.data.source.remote.SignupService
import com.example.idwalltest.utils.safeApiCall
import retrofit2.Response
import javax.inject.Inject

class SignupRepository @Inject constructor(
    private val service: SignupService,
    private val sharedPref: DefaultSharedPreferences
) {

    suspend fun signup(email: String): Result<SignupResponse> =
        safeApiCall(
            call = { service.signup(SignupRequest(email)) },
            errorMessage = "Error on signup api"
        ).also { result ->
            if (result is Result.Success) sharedPref.saveToken(result.data.user.token)
        }
}