package com.ml.product.search.ui.view.adapter.delegates

import android.opengl.Visibility
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ml.product.search.R
import com.ml.product.search.ui.view.adapter.ViewType
import com.ml.product.search.ui.view.adapter.items.ProductItem
import com.ml.product.search.ui.view.inflate
import kotlinx.android.synthetic.main.product_details_view.view.*
import kotlinx.android.synthetic.main.product_item_view.view.image
import kotlinx.android.synthetic.main.product_item_view.view.location
import kotlinx.android.synthetic.main.product_item_view.view.price
import kotlinx.android.synthetic.main.product_item_view.view.title


/**
 * Delegate Adapter for Product Details Type
 */
class ProductDetailsDelegateAdapter() : DelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        if (holder is ViewHolder) {
            holder.bind(item as ProductItem)
        }
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.product_details_view)
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
            } ?: let { location.visibility = View.GONE }

            item.details?.let {
                Glide.with(context)
                    .load(it.imageUrl)
                    .placeholder(R.drawable.loader_clock)
                    .error(R.drawable.error_placeholder)
                    .into(image)


                it.quantity?.let { quantityText ->
                    quantity.text = String.format(
                        context.getString(
                            R.string.quantity
                        ),
                        quantityText
                    )
                } ?: let { quantity.visibility = View.GONE }

                it.condition?.let { conditionText ->
                    condition.text = String.format(
                        context.getString(
                            R.string.condition
                        ),
                        conditionText
                    )
                } ?: let { condition.visibility = View.GONE }

                it.warranty?.let { warrantyText ->
                    warranty.text = String.format(
                        context.getString(
                            R.string.warranty
                        ),
                        warrantyText
                    )
                } ?: let { condition.visibility = View.GONE }

                paymentMethod.text = String.format(
                    context.getString(
                        R.string.paymentMethod
                    ),
                    context.getString(if (it.mpPaymentEnabled) R.string.mppayment else R.string.other)
                )

                it.seller.let { seller ->
                    nickName.text =
                        String.format(
                            context.getString(R.string.seller_nick_name),
                            seller.nickname ?: ""
                        )
                    sellerState.text =
                        String.format(context.getString(R.string.seller_state), seller.state ?: "")
                    sellerReputation.text = String.format(
                        context.getString(R.string.seller_reputation),
                        seller.sellerReputation ?: ""
                    )
                }

            }

        }
    }
}

