package com.iapps.data.feeds

import com.google.gson.annotations.SerializedName
import kotlinx.datetime.LocalDateTime

data class PhotoDto(
    val title: String,
    val link: String,
    val media: MediaDto,
    @SerializedName("date_taken")
    val dateTaken: String, // expected format: 2024-01-15T22:56:41-08:00
    val description: String, // expected format: <p><a href=\"https:\\/\\/www.flickr.com\\/people\\/acb\\/\">acb<\\/a> posted a photo:<\\/p>
    val published: LocalDateTime, // expected format (ISO-8601): 2024-01-18T14:27:48Z
    val author: String, // expected format: nobody@flickr.com (\"Pireddone\")
    @SerializedName("author_id")
    val authorId: String,
    val tags: String, // expected format: "zampe female cleopatra"
)