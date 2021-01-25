package com.ml.product.search.repository

import com.ml.product.search.repository.api.MLApiManager
import com.ml.product.search.repository.model.*
import com.ml.product.search.repository.model.Results.Failure
import com.ml.product.search.repository.model.Results.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MLRepositoryImpl @Inject constructor(
    private val mlApiManager: MLApiManager
) : MLRepository {

    override suspend fun getSites(): Flow<Results<Sites>> = flow {
        emit(mlApiManager.getSites())
    }

    override suspend fun getItem(id: String): Flow<Results<Item>> = flow {
        emit(mlApiManager.getItem(id))
    }

    override suspend fun getUser(id: String): Flow<Results<User>> = flow {
        emit(mlApiManager.getUser(id))
    }

    override suspend fun searchItem(
        site: String,
        query: String,
        offset: String?,
        limit: String?
    ): Flow<Results<ItemResults>> = flow {
        emit(mlApiManager.searchItem(site, query, offset, limit))
    }

    override suspend fun searchProduct(
        site: String,
        query: String,
        offset: String?,
        limit: String?
    ): Flow<Results<List<Product>>> = flow {
        emit(getProductList(site, query, offset, limit))
    }

    override suspend fun getProductDetails(product: Product): Flow<Results<Product>> = flow {
        val itemId = product.id
        val sellerId = product.sellerId

        val itemFlow = flowOf(mlApiManager.getItem(itemId))
        val sellerFlow = flowOf(mlApiManager.getUser(sellerId))

        itemFlow.zip(sellerFlow) { itemResult, sellerResult ->
            when {
                itemResult is Success && sellerResult is Success -> {
                    val productNew = Product(
                        id = product.id,
                        title = product.title,
                        sellerId = product.sellerId,
                        iconUrl = product.iconUrl,
                        price = product.price,
                        locationName = itemResult.value.sellerAddress.state?.name,
                        currency = product.currency,
                        seller = sellerResult.value,
                        item = itemResult.value
                    )
                    return@zip Success(productNew)
                }

                itemResult is Failure -> return@zip Failure<Product>(
                    errorTitle = itemResult.errorTitle,
                    errorMessage = itemResult.errorMessage
                )
                sellerResult is Failure -> return@zip Failure<Product>(
                    errorTitle = sellerResult.errorTitle,
                    errorMessage = sellerResult.errorMessage
                )

                else -> return@zip Failure<Product>(
                    errorTitle = ERROR_TITLE,
                    errorMessage = UNKNOWN_ERROR
                )
            }
        }.collect { emit(it) }

    }

    private suspend fun getProductList(
        site: String,
        query: String,
        offset: String?,
        limit: String?
    ): Results<List<Product>> =
        when (val results = mlApiManager.searchItem(site, query, offset, limit)) {
            is Success -> validateProductList(results)
            is Failure -> {
                Failure(
                    errorTitle = results.errorTitle,
                    errorMessage = results.errorMessage,
                    error = results.error
                )
            }
        }

    private suspend fun validateProductList(results: Success<ItemResults>): Results<List<Product>> =
        if (!results.value.results.isNullOrEmpty()) {
            withContext(Dispatchers.Default) {
                val list = results.value.results
                    .map { result ->
                        Product(
                            id = result.id,
                            sellerId = result.seller.id.toString(),
                            paging = results.value.paging,
                            title = result.title,
                            iconUrl = result.thumbnail,
                            currency = result.currencyId,
                            price = result.price.toString(),
                            locationName = result.sellerAddress.state?.name
                        )
                    }.toList()
                Success(list)
            }
        } else Failure(
            errorTitle = ERROR_TITLE,
            errorMessage = ERROR_EMPTY_PRODUCTS_MESSAGE
        )

}
