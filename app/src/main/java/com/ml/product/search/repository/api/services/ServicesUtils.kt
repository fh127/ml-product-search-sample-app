package com.ml.product.search.repository.api.services

import android.util.Log
import com.ml.product.search.repository.API_ERROR_MESSAGE
import com.ml.product.search.repository.ERROR_TITLE
import com.ml.product.search.repository.UNKNOWN_ERROR
import com.ml.product.search.repository.model.Results
import com.ml.product.search.repository.model.Results.Failure
import com.ml.product.search.repository.model.Results.Success
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

// API Constants
private const val TAG = "ServiceUtils"

/**
 * This method handles response call and errors for API client
 */
fun <T> handleResponse(call: Call<T>): Results<T> {
    return try {
        val response: Response<T> = call.execute()
        if (response.isSuccessful && response.body() != null) {
            Success(response.body()!!)
        } else {
            val errorMessage =
                response.errorBody()?.string() ?: response.message() ?: API_ERROR_MESSAGE
            Failure(
                errorTitle = ERROR_TITLE,
                errorMessage = errorMessage
            )
        }
    } catch (exception: IOException) {
        Log.d(TAG, exception.message ?: UNKNOWN_ERROR)
        Failure(
            errorTitle = ERROR_TITLE,
            errorMessage = UNKNOWN_ERROR,
            error = exception
        )
    }
}

