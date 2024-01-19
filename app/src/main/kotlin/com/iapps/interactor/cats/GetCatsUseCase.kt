package com.iapps.interactor.cats

interface GetCatsUseCase {

    suspend operator fun invoke(): Result<List<Cat>>
}