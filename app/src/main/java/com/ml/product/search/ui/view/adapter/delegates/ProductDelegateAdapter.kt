package com.ml.product.search.ui.view.adapter.delegates

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ml.product.search.R
import com.ml.product.search.ui.view.adapter.ViewType
import com.ml.product.search.ui.view.adapter.items.ProductItem
import com.ml.product.search.ui.view.inflate
import kotlinx.android.synthetic.main.product_item_view.view.*


/**
 * Delegate Adapter for Product View Type
 */
class ProductDelegateAdapter(val listener: OnClickDelegateListener? = null) : DelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        if (holder is ViewHolder) {
            holder.bind(item as ProductItem, listener)
        }
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.product_item_view)
    ) {
        fun bind(item: ProductItem, listener: OnClickDelegateListener? = null) = with(itemView) {
            title.text = item.title
            item.currency?.let {
                price.text = String.format(
                    context.getString(
                        R.string.product_price,
                        item.currency,
                        item.price
                    )
                )
                price.visibility = View.VISIBLE
            }
            item.location?.let {
                location.text = "${item.location}"
                location.visibility = View.VISIBLE
            }

            Glide.with(context)
                .load(item.imageIconUrl)
                .centerCrop()
                .placeholder(R.drawable.loader_clock)
                .error(R.drawable.error_placeholder)
                .into(image)

            listener?.let { listener ->
                setOnClickListener {
                    listener.onClick(item)
                }
            }
        }
    }
}
