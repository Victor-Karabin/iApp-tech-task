package com.iapps.boundary.cats

import kotlinx.datetime.LocalDateTime

data class CatPhoto(
    val title: String,
    val link: String,
    val imageUrl: String,
    val published: LocalDateTime,
    val descriptionHtml: String,
    val authorId: String
)