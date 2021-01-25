package com.ml.product.search.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ml.product.search.repository.MLRepository
import com.ml.product.search.repository.api.services.NetworkUtils
import com.ml.product.search.repository.defaultErrorConnection
import com.ml.product.search.repository.model.Product
import com.ml.product.search.repository.model.Results
import com.ml.product.search.repository.unknownError
import com.ml.product.search.ui.toProductItems
import com.ml.product.search.ui.view.adapter.items.ProductItem
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

const val DEFAULT_OFFSET = "0"
const val DEFAULT_LIMIT = "50"

/**
 * View model class for Products Search screen
 */
class ProductSearchViewModel @Inject constructor(
    private val networkUtils: NetworkUtils,
    private val mlRepository: MLRepository
) : ViewModel() {

    private val _productsLiveData = MutableLiveData<Results<List<ProductItem>>>()
    val productsLiveData: LiveData<Results<List<ProductItem>>> = _productsLiveData

    private var site: String? = null

    fun setSite(site: String) {
        this.site = site
    }

    fun searchProduct(query: String) {
        viewModelScope.launch {
            if (networkUtils.isNetworkConnectionEnabled) {
                mlRepository.searchProduct(site!!, query, DEFAULT_OFFSET, DEFAULT_LIMIT)
                    .catch { e ->
                        println("exception ${e.message}")
                        _productsLiveData.value = unknownError()
                    }.collect {
                        handleResult(it)
                    }
            } else {
                _productsLiveData.value = defaultErrorConnection()
            }
        }
    }

    private fun handleResult(results: Results<List<Product>>) = when (results) {
        is Results.Success -> {
            _productsLiveData.value = Results.Success(results.value.toProductItems())
        }
        is Results.Failure -> {
            _productsLiveData.value = results as Results.Failure<List<ProductItem>>
        }
    }

}
