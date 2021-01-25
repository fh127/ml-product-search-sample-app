package com.ml.product.search.repository.model


import com.google.gson.annotations.SerializedName

/**
 * Dto class for response from
 * ML Search end poFloat (https://api.mercadolibre.com/sites/{site}/search)
 */
data class ItemResults(
    @SerializedName("available_filters")
    val availableFilters: List<AvailableFilter>,
    @SerializedName("available_sorts")
    val availableSorts: List<AvailableSort>,
    val filters: List<Filter>,
    val paging: Paging,
    val query: String,
    @SerializedName("related_results")
    val relatedResults: List<Any>,
    val results: List<Result>,
    @SerializedName("secondary_results")
    val secondaryResults: List<Any>,
    @SerializedName("site_id")
    val siteId: String,
    val sort: Sort
) {
    data class AvailableFilter(
        val id: String,
        val name: String,
        val type: String,
        val values: List<Value>
    ) {
        data class Value(
            val id: String,
            val name: String,
            val results: Float
        )
    }

    data class AvailableSort(
        val id: String,
        val name: String
    )

    data class Filter(
        val id: String,
        val name: String,
        val type: String,
        val values: List<Value>
    ) {
        data class Value(
            val id: String,
            val name: String,
            @SerializedName("path_from_root")
            val pathFromRoot: List<Any>
        )
    }

    data class Paging(
        val limit: Float,
        val offset: Float,
        @SerializedName("primary_results")
        val primaryResults: Float,
        val total: Float
    )

    data class Result(
        @SerializedName("accepts_mercadopago")
        val acceptsMercadopago: Boolean,
        val address: Address,
        val attributes: List<Attribute>,
        @SerializedName("available_quantity")
        val availableQuantity: Float,
        @SerializedName("buying_mode")
        val buyingMode: String,
        @SerializedName("catalog_product_id")
        val catalogProductId: Any?,
        @SerializedName("category_id")
        val categoryId: String,
        val condition: String,
        @SerializedName("currency_id")
        val currencyId: String?,
        @SerializedName("domain_id")
        val domainId: String,
        val id: String,
        val installments: Installments,
        @SerializedName("listing_type_id")
        val listingTypeId: String,
        @SerializedName("official_store_id")
        val officialStoreId: Any?,
        @SerializedName("order_backend")
        val orderBackend: Float,
        @SerializedName("original_price")
        val originalPrice: Float,
        val permalink: String,
        val price: Float,
        val prices: Prices,
        @SerializedName("sale_price")
        val salePrice: Any?,
        val seller: Seller,
        @SerializedName("seller_address")
        val sellerAddress: SellerAddress,
        val shipping: Shipping,
        @SerializedName("site_id")
        val siteId: String,
        @SerializedName("sold_quantity")
        val soldQuantity: Float,
        @SerializedName("stop_time")
        val stopTime: String,
        val tags: List<String>,
        val thumbnail: String,
        val title: String

    ) {
        data class Address(
            @SerializedName("city_id")
            val cityId: String,
            @SerializedName("city_name")
            val cityName: String,
            @SerializedName("state_id")
            val stateId: String,
            @SerializedName("state_name")
            val stateName: String
        )

        data class Attribute(
            @SerializedName("attribute_group_id")
            val attributeGroupId: String,
            @SerializedName("attribute_group_name")
            val attributeGroupName: String,
            val id: String,
            val name: String,
            val source: Long,
            @SerializedName("value_id")
            val valueId: String,
            @SerializedName("value_name")
            val valueName: String,
            @SerializedName("value_struct")
            val valueStruct: Any?,
            val values: List<Value>
        ) {
            data class Value(
                val id: String,
                val name: String,
                val source: Long,
                val struct: Any?
            )
        }

        data class Installments(
            val amount: Double,
            @SerializedName("currency_id")
            val currencyId: String,
            val quantity: Float,
            val rate: Float
        )

        class Prices()

        data class Seller(
            @SerializedName("car_dealer")
            val carDealer: Boolean,
            val id: Int,
            val permalink: Any?,
            @SerializedName("real_estate_agency")
            val realEstateAgency: Boolean,
            @SerializedName("registration_date")
            val registrationDate: Any?,
            val tags: Any?
        )

        data class SellerAddress(
            @SerializedName("address_line")
            val addressLine: String,
            val city: City,
            val comment: String,
            val country: Country,
            val id: String,
            val latitude: String,
            val longitude: String,
            val state: State?,
            @SerializedName("zip_code")
            val zipCode: String
        ) {
            data class City(
                val id: String,
                val name: String
            )

            data class Country(
                val id: String,
                val name: String
            )

            data class State(
                val id: String,
                val name: String?
            )
        }

        data class Shipping(
            @SerializedName("free_shipping")
            val freeShipping: Boolean,
            @SerializedName("logistic_type")
            val logisticType: String,
            val mode: String,
            @SerializedName("store_pick_up")
            val storePickUp: Boolean,
            val tags: List<String>
        )
    }

    data class Sort(
        val id: String,
        val name: String
    )
}