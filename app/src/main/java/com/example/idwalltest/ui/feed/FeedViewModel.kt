package com.example.idwalltest.ui.feed

import androidx.annotation.IdRes
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import com.example.idwalltest.R
import com.example.idwalltest.data.Result
import com.example.idwalltest.data.models.FeedCategory
import com.example.idwalltest.data.repositories.FeedRepository
import com.example.idwalltest.extensions.hide
import com.example.idwalltest.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject

class FeedViewModel @Inject constructor(
    private val repo: FeedRepository
) : BaseViewModel() {

    @VisibleForTesting
    val feedFilter = MutableLiveData(FeedCategory.HUSKY)
    val feed = feedFilter.switchMap(::mapFeedFilterAndEmitResult)

    private val _zoomImgUrl = MutableLiveData<String?>()
    val zoomImgUrl = _zoomImgUrl.hide()
    val shouldZoomImg: LiveData<Boolean> = _zoomImgUrl.map { it != null }

    fun setFilter(@IdRes itemId: Int) {
        val feedCategory = when (itemId) {
            R.id.filter_hound -> FeedCategory.HOUND
            R.id.filter_labrador -> FeedCategory.LABRADOR
            R.id.filter_pug -> FeedCategory.PUG
            else -> FeedCategory.HUSKY
        }
        feedFilter.value = feedCategory
    }

    private fun mapFeedFilterAndEmitResult(category: FeedCategory): LiveData<List<String>> =
        liveDataWithLoading(Dispatchers.IO) {
            when (val result = repo.getFeed(category)) {
                is Result.Success -> emit(result.data.images)
                is Result.Error -> {
                    _toastText.value = R.string.error_feed_api
                    Timber.e(result.exception)
                }
            }
        }

    fun zoomImage(imgUrl: String?) {
        _zoomImgUrl.value = imgUrl
    }

    fun zoomOutImage() {
        _zoomImgUrl.value = null
    }
}