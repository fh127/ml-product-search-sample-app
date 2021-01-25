package com.ml.product.search.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ml.product.search.repository.MLRepository
import com.ml.product.search.repository.api.services.NetworkUtils
import com.ml.product.search.repository.defaultErrorConnection
import com.ml.product.search.repository.model.Results
import com.ml.product.search.repository.model.Sites
import com.ml.product.search.repository.unknownError
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model class for ML Sites screen
 */
class SiteSelectionViewModel @Inject constructor(
    private val networkUtils: NetworkUtils,
    private val mlRepository: MLRepository
) : ViewModel() {

    private val _sitesLiveData = MutableLiveData<Results<Sites>>()
    val sitesLiveData: LiveData<Results<Sites>> = _sitesLiveData

    fun getSites() {
        viewModelScope.launch {
            if (networkUtils.isNetworkConnectionEnabled) {
                mlRepository.getSites()
                    .catch { e ->
                        println("exception ${e.message}")
                        _sitesLiveData.value = unknownError()
                    }.collect {
                        _sitesLiveData.value = it
                    }
            } else {
                _sitesLiveData.value = defaultErrorConnection()
            }
        }
    }
}
