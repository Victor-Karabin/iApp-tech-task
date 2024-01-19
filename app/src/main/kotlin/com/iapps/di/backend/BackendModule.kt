package com.iapps.di.backend

import com.iapps.data.BackendApiProvider
import com.iapps.data.BackendApiProviderImpl
import com.iapps.data.feeds.FeedsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BackendModule {

    @Provides
    @Singleton
    fun provideBackendApiProvider(): BackendApiProvider {
        return BackendApiProviderImpl()
    }

    @Provides
    @Singleton
    fun provideFeedsApi(
        apiProvider: BackendApiProvider
    ): FeedsApi {
        return apiProvider.provideFeedsApi()
    }
}