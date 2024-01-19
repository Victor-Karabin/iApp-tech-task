package com.iapps.data.cats

import com.iapps.boundary.cats.CatPhoto
import com.iapps.boundary.cats.CatPhotosRepo
import com.iapps.core.IoDispatcher
import com.iapps.data.feeds.FeedsApi
import com.iapps.data.wrapRequest
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CatPhotosRepoImpl @Inject constructor(
    private val feedsApi: FeedsApi,
    private val cache: CatPhotosStore,
    @IoDispatcher
    private val io: CoroutineDispatcher
) : CatPhotosRepo {

    companion object {
        private const val CAT_TAGS = "cat"
    }

    override suspend fun photos(): Result<List<CatPhoto>> {
        val cached = cache.get()
        if (cached.isNotEmpty()) return Result.success(cached)

        return wrapRequest(io) { feedsApi.getPhotoFeeds(CAT_TAGS) }
            .map { feed -> feed.toCatPhotos() }
            .onSuccess { photos -> cache.store(photos) }
    }
}