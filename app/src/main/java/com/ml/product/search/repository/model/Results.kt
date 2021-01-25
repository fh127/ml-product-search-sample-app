package com.ml.product.search.repository.model

/**
 * Sealed Class to handle different responses and errors from data layer and UI layer
 */
sealed class Results<T> {
    data class Success<T>(val value: T) : Results<T>()
    data class Failure<T>(
        val errorTitle: String,
        val errorMessage: String,
        val error: Throwable? = null
    ) : Results<T>()
}
