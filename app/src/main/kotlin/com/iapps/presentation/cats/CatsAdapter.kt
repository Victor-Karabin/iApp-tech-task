package com.iapps.presentation.cats

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

internal class CatsAdapter(
    private val onItemClick: (CatItem) -> Unit
) : ListAdapter<CatItem, CatHolder>(CatsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder {
        return CatHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CatHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item) { onItemClick(item) }
    }

    internal fun setItems(items: List<CatItem>) {
        submitList(items)
    }
}