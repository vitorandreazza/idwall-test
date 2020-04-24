package com.example.idwalltest.di

import android.content.Context
import com.example.idwalltest.IdWallTestApplication
import com.example.idwalltest.di.modules.SignupModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        SignupModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<IdWallTestApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}