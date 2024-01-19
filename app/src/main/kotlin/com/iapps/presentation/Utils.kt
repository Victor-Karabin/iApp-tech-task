package com.iapps.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import kotlinx.coroutines.flow.MutableStateFlow

internal suspend fun <T> MutableStateFlow<Boolean>.progress(action: suspend () -> T): T {
    this.value = true
    val result = action()
    this.value = false
    return result
}

internal fun Context.openLinkInBrowser(link: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    startActivity(intent)
}