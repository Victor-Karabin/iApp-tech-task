package com.iapps.data.feeds

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedsApi {

   @GET("/services/feeds/photos_public.gne")
    suspend fun getPhotoFeeds(
        @Query("tags") tags: String,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1
    ): Response<FeedDto>
}