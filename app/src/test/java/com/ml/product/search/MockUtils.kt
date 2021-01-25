package com.ml.product.search

import com.ml.product.search.repository.model.Item
import com.ml.product.search.repository.model.Product
import com.ml.product.search.repository.model.User
import org.mockito.Mockito


/**
 * mock [Product]
 */
fun mockProduct(): Product {
    val product = Mockito.mock(Product::class.java)
    Mockito.`when`(product.id).thenReturn("id")
    Mockito.`when`(product.sellerId).thenReturn("id")
    Mockito.`when`(product.title).thenReturn("title")
    Mockito.`when`(product.iconUrl).thenReturn("url")
    Mockito.`when`(product.price).thenReturn("34456")
    return product
}

fun mockFullProduct(): Product {
    val product = Mockito.mock(Product::class.java)
    val item = Mockito.mock(Item::class.java)
    val seller = Mockito.mock(User::class.java)
    val sellerReputation = Mockito.mock(User.SellerReputation::class.java)
    val sellerStatus = Mockito.mock(User.Status::class.java)
    val picture = Mockito.mock(Item.Picture::class.java)

    Mockito.`when`(picture.url).thenReturn("mock")
    Mockito.`when`(sellerReputation.powerSellerStatus).thenReturn("mock")
    Mockito.`when`(sellerStatus.siteStatus).thenReturn("mock")
    Mockito.`when`(seller.id).thenReturn(12)
    Mockito.`when`(seller.nickname).thenReturn("nickname")
    Mockito.`when`(seller.sellerReputation).thenReturn(sellerReputation)
    Mockito.`when`(seller.status).thenReturn(sellerStatus)

    Mockito.`when`(item.availableQuantity).thenReturn(12f)
    Mockito.`when`(item.pictures).thenReturn(listOf(picture))
    Mockito.`when`(item.warranty).thenReturn("mock")
    Mockito.`when`(item.condition).thenReturn("mock")
    Mockito.`when`(item.acceptsMercadopago).thenReturn(true)

    Mockito.`when`(product.id).thenReturn("id")
    Mockito.`when`(product.sellerId).thenReturn("id")
    Mockito.`when`(product.title).thenReturn("title")
    Mockito.`when`(product.iconUrl).thenReturn("url")
    Mockito.`when`(product.locationName).thenReturn("city")
    Mockito.`when`(product.price).thenReturn("mock")
    Mockito.`when`(product.item).thenReturn(item)
    Mockito.`when`(product.seller).thenReturn(seller)
    return product
}





