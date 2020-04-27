package com.example.idwalltest.data.repositories

import com.example.idwalltest.data.Result
import com.example.idwalltest.data.models.SignupRequest
import com.example.idwalltest.data.models.SignupResponse
import com.example.idwalltest.data.models.User
import com.example.idwalltest.data.source.local.DefaultSharedPreferences
import com.example.idwalltest.data.source.remote.SignupService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

class SignupRepositoryTest {

    private val signupService: SignupService = mock()
    private val sharedPref: DefaultSharedPreferences = mock()
    private val repository = SignupRepository(signupService, sharedPref)

    @Test
    fun `should signup and save token on shared preferences`() {
        val email = "example@example.com"
        val token = "token"
        val signupRequest = SignupRequest(email)
        val signupResponse = SignupResponse(
            user = User(
                createdAt = "1",
                V = 1,
                id = "1",
                email = email,
                token = token,
                updatedAt = "1"
            )
        )
        runBlocking {
            whenever(signupService.signup(signupRequest))
                .thenReturn(Response.success(signupResponse))

            val result = repository.signup(email)

            verify(signupService).signup(signupRequest)
            verify(sharedPref).saveToken(token)
            assertTrue(result is Result.Success)
            assertEquals(token, (result as Result.Success).data.user.token)
        }
    }
}