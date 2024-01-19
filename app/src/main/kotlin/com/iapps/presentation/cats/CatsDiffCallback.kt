package com.iapps.presentation.cats

import androidx.recyclerview.widget.DiffUtil

class CatsDiffCallback : DiffUtil.ItemCallback<CatItem>() {

    override fun areItemsTheSame(oldItem: CatItem, newItem: CatItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CatItem, newItem: CatItem): Boolean {
        return oldItem.imageUrl == newItem.imageUrl &&
                oldItem.link == newItem.link &&
                oldItem.descriptionHtml == newItem.descriptionHtml
    }
}