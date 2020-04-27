package com.example.idwalltest.ui.signup

import androidx.core.util.PatternsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.idwalltest.R
import com.example.idwalltest.data.Result
import com.example.idwalltest.data.repositories.SignupRepository
import com.example.idwalltest.extensions.hide
import com.example.idwalltest.ui.BaseViewModel
import com.example.idwalltest.ui.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SignupViewModel @Inject constructor(
    private val signupRepository: SignupRepository
) : BaseViewModel() {

    val email = MutableLiveData<String>()
    private val _emailError = MutableLiveData<Int>()
    val emailError = _emailError.hide()
    private val _navigateToFeedEvent = MutableLiveData<Event<Boolean>>()
    val navigateToFeedEvent = _navigateToFeedEvent.hide()

    fun signup() {
        val currentEmail = email.value
        val emailPattern = PatternsCompat.EMAIL_ADDRESS

        if (currentEmail.isNullOrBlank() || !emailPattern.matcher(currentEmail).matches()) {
            _emailError.value = R.string.error_invalid_email
            return
        }
        _emailError.value = null
        requestSignup(currentEmail)
    }

    private fun requestSignup(email: String) = viewModelScope.launchWithLoading(Dispatchers.IO) {
        val result = signupRepository.signup(email)
        withContext(Dispatchers.Main) {
            when (result) {
                is Result.Success -> _navigateToFeedEvent.value = Event(true)
                is Result.Error -> {
                    _toastText.value = R.string.error_signup_api
                    Timber.e(result.exception)
                }
            }
        }
    }
}