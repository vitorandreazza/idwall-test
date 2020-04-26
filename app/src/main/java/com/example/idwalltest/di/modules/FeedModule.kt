package com.example.idwalltest.di.modules

import androidx.lifecycle.ViewModel
import com.example.idwalltest.di.ViewModelFactoryModule
import com.example.idwalltest.di.ViewModelKey
import com.example.idwalltest.ui.feed.FeedFragment
import com.example.idwalltest.ui.feed.FeedViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface FeedModule {

    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class])
    fun feedFragment(): FeedFragment

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    fun bindFeedViewModel(feedViewModel: FeedViewModel): ViewModel
}