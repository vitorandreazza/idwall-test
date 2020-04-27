package com.example.idwalltest.ui

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.experimental.ExperimentalTypeInference

abstract class BaseViewModel : ViewModel() {

    protected val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    protected val _toastText = MutableLiveData<Int>()
    val toastText: LiveData<Int> = _toastText


    fun CoroutineScope.launchWithLoading(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) {
        _isLoading.value = true
        launch(context, start) {
            block()
            withContext(Dispatchers.Main) {
                _isLoading.value = false
            }
        }
    }

    @OptIn(ExperimentalTypeInference::class)
    protected fun <T> liveDataWithLoading(
        context: CoroutineContext = EmptyCoroutineContext,
        @BuilderInference call: suspend LiveDataScope<T>.() -> Unit
    ): LiveData<T> {
        _isLoading.value = true
        return liveData(context) {
            call()
            withContext(Dispatchers.Main) { _isLoading.value = false }
        }
    }

}