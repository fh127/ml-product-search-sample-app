package com.ml.product.search.repository.api

import com.ml.product.search.repository.model.*

/**
 * Interface to implement API client logic
 */
interface MLApiManager {

    fun api(): MLApi

    suspend fun getSites(): Results<Sites>

    suspend fun getItem(id : String): Results<Item>

    suspend fun getUser(id : String): Results<User>

    suspend fun searchItem(
        site: String,
        query: String,
        offset: String? = null,
        limit: String? = null
    ): Results<ItemResults>
}