package com.example.idwalltest.ui.signup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.idwalltest.CoroutinesTestRule
import com.example.idwalltest.R
import com.example.idwalltest.data.Result
import com.example.idwalltest.data.repositories.SignupRepository
import com.example.idwalltest.getOrAwaitValue
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import java.io.IOException

@ExperimentalCoroutinesApi
class SignupViewModelTest {

    @get:Rule val coroutinesTestRule = CoroutinesTestRule()
    @get:Rule val instantExecutorRule = InstantTaskExecutorRule()

    private val signupRepository = mock<SignupRepository>()
    private val viewModel = SignupViewModel(signupRepository)

    @Test
    fun `should emit email error when email is empty`() = runBlockingTest {
        viewModel.apply {
            email.value = ""

            signup()

            assertEquals(R.string.error_invalid_email, emailError.value)
            assertNull(navigateToFeedEvent.value)
        }
    }

    @Test
    fun `should emit email error when email is invalid`() = runBlockingTest {
        viewModel.apply {
            email.value = "invalidemail"

            signup()

            assertEquals(R.string.error_invalid_email, emailError.getOrAwaitValue())
            assertNull(navigateToFeedEvent.value)
        }
    }

    @Test
    fun `should emit navigate to feed when signup is success`() = runBlockingTest {
        val email = "example@example.com"
        whenever(signupRepository.signup(email)).thenReturn(Result.Success(mock()))

        viewModel.apply {
            this.email.value = email

            signup()

            assertTrue(navigateToFeedEvent.getOrAwaitValue().peekContent())
        }
    }

    @Test
    fun `should emit error when signup fails`() = runBlockingTest {
        whenever(signupRepository.signup(anyString())).thenReturn(Result.Error(IOException()))

        viewModel.apply {
            email.value = "example@example.com"

            signup()

            assertNull(navigateToFeedEvent.value)
            assertEquals(R.string.error_signup_api, toastText.getOrAwaitValue())
        }
    }
}