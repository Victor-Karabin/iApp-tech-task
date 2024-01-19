package com.iapps.data.cats

import com.iapps.boundary.cats.CatPhoto

interface CatPhotosStore {

    suspend fun store(photos: List<CatPhoto>)

    suspend fun get(): List<CatPhoto>
}