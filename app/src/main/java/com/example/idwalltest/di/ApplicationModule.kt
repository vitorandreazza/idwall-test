package com.example.idwalltest.di

import android.content.Context
import android.content.SharedPreferences
import com.example.idwalltest.BuildConfig
import com.example.idwalltest.data.source.local.DefaultSharedPreferences
import com.example.idwalltest.data.source.remote.FeedService
import com.example.idwalltest.data.source.remote.ServiceGenerator
import com.example.idwalltest.data.source.remote.SignupService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideSignupService(serviceGenerator: ServiceGenerator) =
        serviceGenerator.createService(BuildConfig.BASE_URL, SignupService::class.java)

    @Singleton
    @Provides
    fun provideFeedService(
        serviceGenerator: ServiceGenerator,
        sharedPref: DefaultSharedPreferences
    ) =
        serviceGenerator.createService(
            BuildConfig.BASE_URL,
            FeedService::class.java,
            sharedPref.getToken()
        )

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(context.packageName + "_preferences", Context.MODE_PRIVATE)
}
