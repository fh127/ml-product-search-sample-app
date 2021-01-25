package com.ml.product.search.repository.api

import com.ml.product.search.repository.model.Item
import com.ml.product.search.repository.model.ItemResults
import com.ml.product.search.repository.model.Sites
import com.ml.product.search.repository.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val SERVICE_END_POINT = "https://api.mercadolibre.com"
private const val SITES = "/sites"
private const val ITEM_SEARCH = "$SITES/{site}/search"
private const val ITEM= "/items/{itemId}"
private const val USER = "/users/{userId}"

/**
 * Interface to define ML API end points
 */
interface MLApi {

    @GET(SITES)
    fun getSites(): Call<Sites>

    @GET(ITEM)
    fun getItem(@Path("itemId") itemId: String): Call<Item>

    @GET(USER)
    fun getUser(@Path("userId") userId: String): Call<User>

    @GET(ITEM_SEARCH)
    fun searchProduct(
        @Path("site") site: String,
        @Query("q") query: String,
        @Query("offset") offset: String? = null,
        @Query("limit") limit: String? = null
    ): Call<ItemResults>


}
