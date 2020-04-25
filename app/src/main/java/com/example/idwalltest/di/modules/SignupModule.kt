package com.example.idwalltest.di.modules

import androidx.lifecycle.ViewModel
import com.example.idwalltest.di.ViewModelFactoryModule
import com.example.idwalltest.di.ViewModelKey
import com.example.idwalltest.ui.signup.SignupFragment
import com.example.idwalltest.ui.signup.SignupViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface SignupModule {

    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class])
    fun signupFragment(): SignupFragment

    @Binds
    @IntoMap
    @ViewModelKey(SignupViewModel::class)
    fun bindSignupViewModel(signupViewModel: SignupViewModel): ViewModel
}