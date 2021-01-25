package com.ml.product.search.repository

import com.ml.product.search.repository.model.Results

//  Error Messages Constants
const val ERROR_TITLE = "Alert"
const val API_ERROR_MESSAGE = "A service Error has probably occurred!!"
const val ERROR_CONNECTION_MESSAGE = "Can't connect internet!"
const val ERROR_EMPTY_PRODUCTS_MESSAGE = "Oh Sorry, there aren't related products"
const val ERROR_PRODUCTS_DETAILS_MESSAGE =
    "Oh Sorry, there was unknown error related to the details of this product"
const val UNKNOWN_ERROR = "Unknown error"
const val SITE_ERROR = "Oh Sorry! Couldn't get Site to continue, Try again please"
const val PRODUCT_ITEM_ERROR = "Oh Sorry! Couldn't get product information to continue, Try again please"

/**
 * This method return default failure for internet connection error to reuse
 */
fun <T> defaultErrorConnection(): Results.Failure<T> = Results.Failure(
    ERROR_TITLE, ERROR_CONNECTION_MESSAGE
)


/**
 * This method return unknown failure for unknown error to reuse
 */
fun <T> unknownError(): Results.Failure<T> = Results.Failure(
    ERROR_TITLE, UNKNOWN_ERROR
)
