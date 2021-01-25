package com.ml.product.search.repository.model

import com.google.gson.annotations.SerializedName

/**
 * Dto class for response from
 * ML Site end point (https://api.mercadolibre.com/sites)
 */
class Sites : ArrayList<Sites.Site>() {
    data class Site(
        @SerializedName("default_currency_id")
        val defaultCurrencyId: String,
        val id: String,
        val name: String
    )
}

