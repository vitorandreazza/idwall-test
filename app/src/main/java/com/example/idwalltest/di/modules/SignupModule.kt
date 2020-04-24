package com.example.idwalltest.di.modules

import com.example.idwalltest.ui.signup.SignupFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SignupModule {

    @ContributesAndroidInjector//(modules = [ViewModelFactoryModule::class])
    fun signupFragment(): SignupFragment

//    @Binds
//    @IntoMap
//    @ViewModelKey(HomeViewModel::class)
//    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}