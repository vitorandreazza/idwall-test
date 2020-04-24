package com.example.idwalltest.di

import androidx.lifecycle.ViewModelProvider
import com.example.idwalltest.utils.ApplicationViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ApplicationViewModelFactory): ViewModelProvider.Factory
}