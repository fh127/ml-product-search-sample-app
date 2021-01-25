package com.ml.product.search.repository.api.services

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

/**
 * This class verify if the network connection is available
 */
class NetworkUtils @Inject constructor(private val context: Context) {
    val isNetworkConnectionEnabled: Boolean
        get() {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
}