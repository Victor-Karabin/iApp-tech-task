package com.iapps.data

import com.iapps.data.feeds.FeedsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject

class BackendApiProviderImpl @Inject constructor() : BackendApiProvider {

    private val retrofit by lazy {
        val client = OkHttpClient().newBuilder()
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.flickr.com") // TODO move to properties
            .client(client)
            .addConverterFactory(ConverterFactory())
            .build()
    }

    private val feedsApi by lazy {
        retrofit.create(FeedsApi::class.java)
    }

    override fun provideFeedsApi(): FeedsApi = feedsApi
}