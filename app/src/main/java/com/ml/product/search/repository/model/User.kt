package com.ml.product.search.repository.model


import com.google.gson.annotations.SerializedName

/**
 * Dto class for response from
 * ML Search end point (https://api.mercadolibre.com/users/{userId})
 */
data class User(
    val address: Address,
    @SerializedName("buyer_reputation")
    val buyerReputation: BuyerReputation,
    @SerializedName("country_id")
    val countryId: String,
    val id: Int,
    val logo: Any?,
    val nickname: String,
    val permalink: String,
    val points: Int,
    @SerializedName("registration_date")
    val registrationDate: String,
    @SerializedName("seller_reputation")
    val sellerReputation: SellerReputation,
    @SerializedName("site_id")
    val siteId: String,
    val status: Status,
    val tags: List<String>,
    @SerializedName("user_type")
    val userType: String
) {
    data class Address(
        val city: String,
        val state: String
    )

    data class BuyerReputation(
        val tags: List<Any>
    )

    data class SellerReputation(
        @SerializedName("level_id")
        val levelId: String,
        @SerializedName("power_seller_status")
        val powerSellerStatus: String,
        val transactions: Transactions
    ) {
        data class Transactions(
            val canceled: Int,
            val completed: Int,
            val period: String,
            val ratings: Ratings,
            val total: Int
        ) {
            class Ratings(
            )
        }
    }

    data class Status(
        @SerializedName("site_status")
        val siteStatus: String
    )
}
