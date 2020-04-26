package com.example.idwalltest.ui.feed

import android.util.Log
import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.example.idwalltest.R
import com.example.idwalltest.data.Result
import com.example.idwalltest.data.models.FeedCategory
import com.example.idwalltest.data.models.FeedResponse
import com.example.idwalltest.data.repositories.FeedRepository
import com.example.idwalltest.extensions.hide
import com.example.idwalltest.extensions.log
import com.example.idwalltest.ui.BaseViewModel
import com.example.idwalltest.ui.signup.SignupViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class FeedViewModel @Inject constructor(
    private val repo: FeedRepository
) : BaseViewModel() {

    private val feedFilter = MutableLiveData(FeedCategory.HUSKY)
    val feed = feedFilter.switchMap { category ->
        liveData {
            when (val result = repo.getFeed(category)) {
                is Result.Success -> emit(result.data.images)
                is Result.Error -> {
                    _toastText.value = R.string.error_generic
                    Log.e(TAG, result.exception.log())
                }
            }
        }
    }
    private val _zoomImg = MutableLiveData<String?>()
    val zoomImg = _zoomImg.hide()

    fun setFilter(@IdRes itemId: Int) {
        val feedCategory = when (itemId) {
            R.id.filter_hound -> FeedCategory.HOUND
            R.id.filter_labrador -> FeedCategory.LABRADOR
            R.id.filter_pug -> FeedCategory.PUG
            else -> FeedCategory.HUSKY
        }
        feedFilter.value = feedCategory
    }

    fun zoomImage(imgUrl: String?) {
        _zoomImg.value = imgUrl
    }

    companion object {
        private val TAG = FeedViewModel::class.java.simpleName
    }
}