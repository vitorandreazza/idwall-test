package com.example.idwalltest.ui.feed

import androidx.annotation.IdRes
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.idwalltest.CoroutinesTestRule
import com.example.idwalltest.R
import com.example.idwalltest.data.Result
import com.example.idwalltest.data.models.FeedCategory
import com.example.idwalltest.data.models.FeedResponse
import com.example.idwalltest.data.repositories.FeedRepository
import com.example.idwalltest.getOrAwaitValue
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FeedViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository: FeedRepository = mock()
    private val viewModel: FeedViewModel = FeedViewModel(repository)

    @Test
    fun `should map id filter hound to category hound`() {
        testIdFilterToCategory(R.id.filter_hound, FeedCategory.HOUND)
    }

    @Test
    fun `should map id filter labrador to category labrador`() {
        testIdFilterToCategory(R.id.filter_labrador, FeedCategory.LABRADOR)
    }

    @Test
    fun `should map id filter pug to category pug`() {
        testIdFilterToCategory(R.id.filter_pug, FeedCategory.PUG)
    }

    @Test
    fun `should map id filter husky to category husky`() {
        testIdFilterToCategory(R.id.filter_husky, FeedCategory.HUSKY)
    }

    private fun testIdFilterToCategory(@IdRes id: Int, category: FeedCategory) {
        viewModel.apply {
            setFilter(id)

            assertEquals(category, feedFilter.getOrAwaitValue())
        }
    }

    @Test
    fun `should emit feed images if feed success`() = runBlockingTest {
        val category = FeedCategory.HUSKY
        val images = listOf("url", "url", "url")
        val feedResponse = FeedResponse(category = "husky", images = images)
        whenever(repository.getFeed(category)).thenReturn(Result.Success(feedResponse))

        viewModel.apply {
            setFilter(R.id.filter_husky)

            assertEquals(images, feed.getOrAwaitValue())
        }
    }
}