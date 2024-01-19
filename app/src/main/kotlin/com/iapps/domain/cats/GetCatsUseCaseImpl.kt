package com.iapps.domain.cats

import com.iapps.boundary.cats.CatPhotosRepo
import com.iapps.interactor.cats.Cat
import com.iapps.interactor.cats.GetCatsUseCase
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import javax.inject.Inject

class GetCatsUseCaseImpl @Inject constructor(
    private val catPhotosRepo: CatPhotosRepo
) : GetCatsUseCase {

    override suspend fun invoke(): Result<List<Cat>> {
        return catPhotosRepo.photos()
            .map { photos ->
                photos.sortedBy { photo -> photo.published.toInstant(TimeZone.UTC).epochSeconds }
                    .map { photo -> photo.toCat() }
            }
    }
}