package com.ml.product.search.ui.view.adapter.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ml.product.search.R
import com.ml.product.search.ui.view.adapter.ViewType
import com.ml.product.search.ui.view.adapter.items.SiteItem
import com.ml.product.search.ui.view.inflate
import kotlinx.android.synthetic.main.site_item_view.view.*

/**
 * Delegate Adapter for Sites View Type
 */
class SiteDelegateAdapter(val listener: OnClickDelegateListener? = null) : DelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        if (holder is ViewHolder) {
            holder.bind(item as SiteItem, listener)
        }
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.site_item_view)
    ) {
        fun bind(item: SiteItem, listener: OnClickDelegateListener? = null) = with(itemView) {
            siteName.text = item.name
            listener?.let { listener ->
                setOnClickListener {
                    listener.onClick(item)
                }
            }
        }
    }
}