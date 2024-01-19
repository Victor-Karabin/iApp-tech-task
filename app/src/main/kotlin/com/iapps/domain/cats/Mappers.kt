package com.iapps.domain.cats

import com.iapps.boundary.cats.CatPhoto
import com.iapps.interactor.cats.Cat

internal fun CatPhoto.toCat(): Cat {
    return Cat(
        id = "${this.authorId}-${this.published}",
        title = this.title,
        imageUrl = this.imageUrl,
        link = this.link,
        descriptionHtml = this.descriptionHtml
    )
}