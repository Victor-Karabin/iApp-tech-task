package com.iapps.di.cats

import com.iapps.boundary.cats.CatPhotosRepo
import com.iapps.data.cats.CatPhotosRepoImpl
import com.iapps.data.cats.CatPhotosStore
import com.iapps.data.cats.CatPhotosStoreImpl
import com.iapps.domain.cats.GetCatsUseCaseImpl
import com.iapps.interactor.cats.GetCatsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface CatsModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelFactory

    @Binds
    fun bindsGetCatsUseCase(useCase: GetCatsUseCaseImpl): GetCatsUseCase

    @Binds
    fun bindCatPhotosRepo(repo: CatPhotosRepoImpl): CatPhotosRepo

    @Binds
    fun bindCatPhotosStore(store: CatPhotosStoreImpl): CatPhotosStore
}