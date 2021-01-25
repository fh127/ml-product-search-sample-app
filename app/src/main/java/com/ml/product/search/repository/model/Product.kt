package com.ml.product.search.repository.model

/**
 * Model class to encapsulate the data for product search logic
 */
class Product(
    val id: String,
    val sellerId: String,
    val title: String,
    val iconUrl: String,
    val currency: String?,
    val price: String,
    val locationName: String? = null,
    val paging: ItemResults.Paging? = null,
    val item: Item? = null,
    val seller: User? = null
)
