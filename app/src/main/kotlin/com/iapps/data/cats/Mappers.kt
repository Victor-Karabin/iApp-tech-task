package com.iapps.data.cats

import com.iapps.boundary.cats.CatPhoto
import com.iapps.data.feeds.FeedDto
import com.iapps.data.feeds.PhotoDto

internal fun FeedDto.toCatPhotos(): List<CatPhoto> {
    return this.items.map { photoDto ->
        photoDto.toCatPhoto()
    }
}

private fun PhotoDto.toCatPhoto(): CatPhoto {
    return CatPhoto(
        title = this.title,
        link = this.link,
        imageUrl = this.media.mediaUrl,
        published = this.published,
        descriptionHtml = this.description,
        authorId = this.authorId
    )
}