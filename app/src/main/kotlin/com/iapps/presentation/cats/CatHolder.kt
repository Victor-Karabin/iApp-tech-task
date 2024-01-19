package com.iapps.presentation.cats

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aiapps.R

internal class CatHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    companion object {
        internal fun create(parent: ViewGroup): CatHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cat, parent, false)

            return CatHolder(view)
        }
    }

    private val imageView = itemView.findViewById<ImageView>(R.id.imv_cat)
    private val tvDescription = itemView.findViewById<TextView>(R.id.tv_description)

    fun bind(item: CatItem, itemClickListener: View.OnClickListener) {
        itemView.setOnClickListener(itemClickListener)

        imageView.contentDescription = item.title
        imageView.load(item.imageUrl) {
            crossfade(true)
        }

        tvDescription.text = Html.fromHtml(item.descriptionHtml, Html.FROM_HTML_MODE_COMPACT)
    }
}