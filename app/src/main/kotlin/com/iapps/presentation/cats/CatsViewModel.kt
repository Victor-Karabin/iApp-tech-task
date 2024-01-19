package com.iapps.presentation.cats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iapps.interactor.cats.GetCatsUseCase
import com.iapps.presentation.progress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(
    private val getCats: GetCatsUseCase
) : ViewModel() {

    private val mutableCatItems = MutableStateFlow(emptyList<CatItem>())
    internal val catItems = mutableCatItems.asStateFlow()

    private val mutableProgress = MutableStateFlow(false)
    internal val progress = mutableProgress.asStateFlow()

    private val mutableError = MutableStateFlow("")
    internal val error = mutableError.asStateFlow()

    internal fun loadItems() {
        viewModelScope.launch {
            mutableProgress.progress { getCats() }
                .onSuccess { cats -> mutableCatItems.value = cats.map { cat -> cat.toItem() } }
                .onFailure { error -> mutableError.value = error.message ?: error.toString() }
        }
    }
}