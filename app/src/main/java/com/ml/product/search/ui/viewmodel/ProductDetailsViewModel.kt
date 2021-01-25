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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model class for Products Details screen
 */
class ProductDetailsViewModel @Inject constructor(
    private val networkUtils: NetworkUtils,
    private val mlRepository: MLRepository
) : ViewModel() {
    private val _productDetailsLiveData = MutableLiveData<Results<List<Product>>>()
    val productDetailsLiveData: LiveData<Results<List<Product>>> = _productDetailsLiveData

    fun getDetails(item: Product) {
        viewModelScope.launch {
            if (networkUtils.isNetworkConnectionEnabled) {
                mlRepository.getProductDetails(item)
                    .catch { e ->
                        println("exception ${e.message}")
                        _productDetailsLiveData.value = unknownError()
                    }.collect {
                        handleResult(it)
                    }
            } else {
                _productDetailsLiveData.value = defaultErrorConnection()
            }

        }
    }

    private fun handleResult(results: Results<Product>) = when (results) {
        is Results.Success -> {
            _productDetailsLiveData.value = Results.Success(listOf(results.value))
        }
        is Results.Failure -> {
            _productDetailsLiveData.value = results as Results.Failure<List<Product>>
        }
    }

}

