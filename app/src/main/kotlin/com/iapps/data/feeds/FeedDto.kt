package com.iapps.data.feeds

import kotlinx.datetime.LocalDateTime

data class FeedDto (
    val title: String,
    val link: String,
    val description: String,
    val modified: LocalDateTime, // expected format (ISO-8601): 2024-01-18T14:27:48Z
    val generator: String,
    val items: List<PhotoDto>
)