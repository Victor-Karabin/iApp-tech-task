package com.iapps.data.cats

import com.iapps.boundary.cats.CatPhoto
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class CatPhotosStoreImpl @Inject constructor() : CatPhotosStore {

    private val locker = Mutex()
    private val data = ArrayList<CatPhoto>()

    override suspend fun store(photos: List<CatPhoto>) {
        locker.withLock {
            data.clear()
            data.addAll(photos)
        }
    }

    override suspend fun get(): List<CatPhoto> {
        return data
    }
}