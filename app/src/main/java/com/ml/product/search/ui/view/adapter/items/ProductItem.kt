package com.ml.product.search.ui.view.adapter.items

import com.ml.product.search.ui.view.adapter.PRODUCT_TYPE
import com.ml.product.search.ui.view.adapter.ViewType
import java.io.Serializable

/**
 *Item class for [com.ml.product.search.ui.view.adapter.delegates.ProductDelegateAdapter]
 */
data class ProductItem(
    val id: String,
    val sellerId: String,
    val imageIconUrl: String,
    val title: String,
    val price: String,
    val currency: String?,
    val location: String?,
    val details: Details? = null
) : ViewType, Serializable {
    override fun getViewType(): Int = PRODUCT_TYPE
    data class Details(
        val imageUrl: String?,
        val quantity: String?,
        val warranty: String?,
        val condition: String?,
        val seller: Seller,
        val mpPaymentEnabled: Boolean
    ) {
        data class Seller(
            val nickname: String?,
            val state: String?,
            val sellerReputation: String?
        )
    }

}
