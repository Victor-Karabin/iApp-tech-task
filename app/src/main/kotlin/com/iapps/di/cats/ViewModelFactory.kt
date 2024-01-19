package com.iapps.di.cats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iapps.interactor.cats.GetCatsUseCase
import com.iapps.presentation.cats.CatsViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == CatsViewModel::class.java)
        return CatsViewModel(getCatsUseCase) as T
    }
}