package com.ml.product.search.ui

import com.ml.product.search.repository.model.Product
import com.ml.product.search.repository.model.Sites
import com.ml.product.search.ui.view.adapter.items.ProductItem
import com.ml.product.search.ui.view.adapter.items.SiteItem

/**
 * This method converts a Site list to SiteItem List
 */
fun List<Sites.Site>.toSiteItems(): List<SiteItem> {
    return if (isNotEmpty()) {
        map {
            it.toItem()
        }
    } else emptyList()
}

/**
 * This method converts a [Sites.Site] to [SiteItem]
 */
fun Sites.Site.toItem(): SiteItem = SiteItem(
    name = this.name,
    id = this.id
)

/**
 * This method converts a Product list to ProductItem List
 */
fun List<Product>.toProductItems(): List<ProductItem> {
    return if (isNotEmpty()) {
        map {
            it.toItem()
        }
    } else emptyList()
}


/**
 * This method converts a [Product] to [ProductItem]
 */
fun Product.toItem(): ProductItem = ProductItem(
    id = this.id,
    title = this.title,
    currency = this.currency,
    price = this.price,
    imageIconUrl = this.iconUrl,
    location = this.locationName,
    sellerId = this.sellerId
)


/**
 * This method converts a Product list to ProductItem List
 */
fun List<Product>.toProductItemDetails(): List<ProductItem> {
    return if (isNotEmpty()) {
        map {
            it.toDetails()
        }
    } else emptyList()
}

/**
 * This method converts a [Product] to [ProductItem]
 * with details
 */
fun Product.toDetails(): ProductItem = ProductItem(
    id = this.id,
    title = this.title,
    currency = this.currency,
    price = this.price,
    imageIconUrl = this.iconUrl,
    location = this.locationName,
    sellerId = this.sellerId,
    details = ProductItem.Details(
        imageUrl = this.item?.pictures?.first()?.url,
        quantity = this.item?.availableQuantity.toString(),
        warranty = this.item?.warranty,
        condition = this.item?.condition,
        mpPaymentEnabled = this.item?.acceptsMercadopago ?: false,
        seller = ProductItem.Details.Seller(
            nickname = this.seller?.nickname,
            sellerReputation = this.seller?.sellerReputation?.powerSellerStatus,
            state = this.seller?.status?.siteStatus
        )
    )
)

/**
 * This method converts a [ProductItem] to [Product]
 * with details
 */
fun ProductItem.toProduct(): Product = Product(
    id = this.id,
    sellerId = this.sellerId,
    title = this.title,
    iconUrl = this.imageIconUrl,
    currency = this.currency,
    price = this.price
)
