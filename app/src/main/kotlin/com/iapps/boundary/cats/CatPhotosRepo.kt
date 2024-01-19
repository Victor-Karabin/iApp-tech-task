package com.iapps.boundary.cats

interface CatPhotosRepo {

    suspend fun photos(): Result<List<CatPhoto>>
}