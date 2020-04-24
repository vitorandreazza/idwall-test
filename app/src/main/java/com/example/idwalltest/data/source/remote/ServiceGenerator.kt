package com.example.idwalltest.data.source.remote

import com.example.idwalltest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceGenerator @Inject constructor() {

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        })

    private val builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())

    fun <T> createService(baseUrl: String, serviceClass: Class<T>): T =
        createService(baseUrl, serviceClass, null)

    fun <T> createService(baseUrl: String, serviceClass: Class<T>, authToken: String?): T {
        if (!authToken.isNullOrBlank()) {
            httpClient.addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", authToken)
                    .build()
                chain.proceed(request)
            }
        }
        return builder
            .baseUrl(baseUrl)
            .client(httpClient.build())
            .build()
            .create(serviceClass)
    }
}