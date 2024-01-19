package com.iapps.domain.cats

import com.iapps.boundary.cats.CatPhoto
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.UUID

class CatPhotoFactory {

    fun create(publishedDate: String): CatPhoto {
        return CatPhoto(
            title = "title#$publishedDate",
            link = "https://cats#$publishedDate.com",
            imageUrl = "httsp://cats.com/image#$publishedDate.jpg",
            published = Instant.parse(publishedDate).toLocalDateTime(TimeZone.UTC),
            descriptionHtml = "<p>cat#$publishedDate</p>",
            authorId = UUID.randomUUID().toString()
        )
    }
}