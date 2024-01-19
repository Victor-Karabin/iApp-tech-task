package com.iapps.presentation.cats

import com.iapps.interactor.cats.Cat

internal fun Cat.toItem(): CatItem {
    return CatItem(
        id = this.id,
        title = this.title,
        imageUrl = this.imageUrl,
        link = this.link,
        descriptionHtml = this.descriptionHtml
    )
}