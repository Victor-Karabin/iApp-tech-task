package com.iapps.data.feeds

import com.google.gson.annotations.SerializedName

data class MediaDto(
    @SerializedName("m")
    val mediaUrl: String
)