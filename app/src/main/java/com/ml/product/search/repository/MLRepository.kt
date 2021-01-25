package com.ml.product.search.repository

import com.ml.product.search.repository.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Interface to define ML Repository
 * and connect to data resource and UI logic
 */
interface MLRepository {

    /**
     * This method gets sites
     */
    suspend fun getSites(): Flow<Results<Sites>>

    /**
     * This method gets item
     */
    suspend fun getItem(id: String): Flow<Results<Item>>

    /**
     * This method gets user
     */
    suspend fun getUser(id: String): Flow<Results<User>>

    /**
     * This method searches items by query and pagination
     */
    suspend fun searchItem(
        site: String,
        query: String,
        offset: String? = null,
        limit: String? = null
    ): Flow<Results<ItemResults>>

    /**
     * this gets products list from query search
     * and transformation of a [Product] model with the used data related to the UI
     */
    suspend fun searchProduct(
        site: String,
        query: String,
        offset: String? = null,
        limit: String? = null
    ): Flow<Results<List<Product>>>


    /**
     * This method gets product details to show in the UI
     */
    suspend fun getProductDetails(product: Product): Flow<Results<Product>>
}
