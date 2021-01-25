package com.ml.product.search.repository.api

import android.util.Log
import com.ml.product.search.repository.API_ERROR_MESSAGE
import com.ml.product.search.repository.UNKNOWN_ERROR
import com.ml.product.search.repository.api.services.RetrofitServiceFactory
import com.ml.product.search.repository.api.services.handleResponse
import com.ml.product.search.repository.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

private const val TAG = "MLApiManager"

class MLApiManagerImpl @Inject constructor(
    private val retrofitServiceFactory: RetrofitServiceFactory
) : MLApiManager {

    private val mlApi: MLApi = retrofitServiceFactory.createRetrofitService(
        MLApi::class.java,
        SERVICE_END_POINT
    )

    override fun api(): MLApi = mlApi

    override suspend fun getSites(): Results<Sites> = withContext(Dispatchers.IO) {
        try {
            handleResponse(mlApi.getSites())
        } catch (exception: IOException) {
            Log.d(TAG, exception.message ?: UNKNOWN_ERROR)
            Results.Failure<Sites>(API_ERROR_MESSAGE, API_ERROR_MESSAGE, exception)
        }
    }

    override suspend fun getItem(id: String): Results<Item> = withContext(Dispatchers.IO) {
        try {
            handleResponse(mlApi.getItem(id))
        } catch (exception: IOException) {
            Log.d(TAG, exception.message ?: UNKNOWN_ERROR)
            Results.Failure<Item>(API_ERROR_MESSAGE, API_ERROR_MESSAGE, exception)
        }
    }

    override suspend fun getUser(id: String): Results<User> = withContext(Dispatchers.IO) {
        try {
            handleResponse(mlApi.getUser(id))
        } catch (exception: IOException) {
            Log.d(TAG, exception.message ?: UNKNOWN_ERROR)
            Results.Failure<User>(API_ERROR_MESSAGE, API_ERROR_MESSAGE, exception)
        }
    }

    override suspend fun searchItem(
        site: String,
        query: String,
        offset: String?,
        limit: String?
    ): Results<ItemResults> = withContext(Dispatchers.IO) {
        try {
            handleResponse(mlApi.searchProduct(site, query, offset, limit))
        } catch (exception: IOException) {
            Log.d(TAG, exception.message ?: UNKNOWN_ERROR)
            Results.Failure<ItemResults>(API_ERROR_MESSAGE, API_ERROR_MESSAGE, exception)
        }
    }
}