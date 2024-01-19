package com.iapps.data

import com.iapps.data.feeds.FeedsApi

interface BackendApiProvider {

    fun provideFeedsApi() : FeedsApi
}