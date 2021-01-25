package com.ml.product.search.repository.model


import com.google.gson.annotations.SerializedName

/**
 * Dto class for response from
 * ML Search end poFloat (https://api.mercadolibre.com/items/{itemId})
 */
data class Item(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean,
    val attributes: List<Attribute>,
    @SerializedName("automatic_relist")
    val automaticRelist: Boolean,
    @SerializedName("available_quantity")
    val availableQuantity: Float,
    @SerializedName("base_price")
    val basePrice: Float,
    @SerializedName("buying_mode")
    val buyingMode: String,
    @SerializedName("catalog_listing")
    val catalogListing: Boolean,
    @SerializedName("catalog_product_id")
    val catalogProductId: Any?,
    @SerializedName("category_id")
    val categoryId: String,
    val condition: String,
    @SerializedName("coverage_areas")
    val coverageAreas: List<Any>,
    @SerializedName("currency_id")
    val currencyId: String,
    @SerializedName("date_created")
    val dateCreated: String,
    @SerializedName("deal_ids")
    val dealIds: List<String>,
    val descriptions: List<Description>,
    @SerializedName("differential_pricing")
    val differentialPricing: Any?,
    @SerializedName("domain_id")
    val domainId: String,
    val health: Double,
    val id: String,
    @SerializedName("initial_quantity")
    val initialQuantity: Float,
    @SerializedName("Floaternational_delivery_mode")
    val FloaternationalDeliveryMode: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("listing_source")
    val listingSource: String,
    @SerializedName("listing_type_id")
    val listingTypeId: String,
    val location: Location,
    @SerializedName("non_mercado_pago_payment_methods")
    val nonMercadoPagoPaymentMethods: List<Any>,
    @SerializedName("official_store_id")
    val officialStoreId: Any?,
    @SerializedName("original_price")
    val originalPrice: Any?,
    @SerializedName("parent_item_id")
    val parentItemId: Any?,
    val permalink: String,
    val pictures: List<Picture>,
    val price: Float,
    @SerializedName("sale_terms")
    val saleTerms: List<SaleTerm>,
    @SerializedName("secure_thumbnail")
    val secureThumbnail: String,
    @SerializedName("seller_address")
    val sellerAddress: SellerAddress,
    @SerializedName("seller_contact")
    val sellerContact: Any?,
    @SerializedName("seller_id")
    val sellerId: Float,
    val shipping: Shipping,
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("sold_quantity")
    val soldQuantity: Float,
    @SerializedName("start_time")
    val startTime: String,
    val status: String,
    @SerializedName("stop_time")
    val stopTime: String,
    @SerializedName("sub_status")
    val subStatus: List<Any>,
    val subtitle: Any?,
    val tags: List<String>,
    val thumbnail: String,
    @SerializedName("thumbnail_id")
    val thumbnailId: String,
    val title: String,
    val variations: List<Variation>,
    @SerializedName("video_id")
    val videoId: Any?,
    val warnings: List<Any>,
    val warranty: String
) {
    data class Attribute(
        @SerializedName("attribute_group_id")
        val attributeGroupId: String,
        @SerializedName("attribute_group_name")
        val attributeGroupName: String,
        val id: String,
        val name: String,
        @SerializedName("value_id")
        val valueId: Any?,
        @SerializedName("value_name")
        val valueName: String,
        @SerializedName("value_struct")
        val valueStruct: ValueStruct,
        val values: List<Any>
    ) {
        data class ValueStruct(
            val number: Float,
            val unit: String
        )
    }

    data class Description(
        val id: String
    )

    class Location(
    )

    data class Picture(
        val id: String,
        @SerializedName("max_size")
        val maxSize: String,
        val quality: String,
        @SerializedName("secure_url")
        val secureUrl: String,
        val size: String,
        val url: String
    )

    data class SaleTerm(
        val id: String,
        val name: String,
        @SerializedName("value_id")
        val valueId: Any?,
        @SerializedName("value_name")
        val valueName: String,
        @SerializedName("value_struct")
        val valueStruct: ValueStruct?,
        val values: List<Value>
    ) {
        data class ValueStruct(
            val number: Float,
            val unit: String
        )

        data class Value(
            val id: Any?,
            val name: String,
            val struct: Struct?
        ) {
            data class Struct(
                val number: Float,
                val unit: String
            )
        }
    }

    data class SellerAddress(
        val city: City,
        val country: Country,
        val id: Float,
        @SerializedName("search_location")
        val searchLocation: SearchLocation,
        val state: State?
    ) {
        data class City(
            val id: String,
            val name: String
        )

        data class Country(
            val id: String,
            val name: String
        )

        data class SearchLocation(
            val city: City,
            val state: State
        ) {
            data class City(
                val id: String,
                val name: String
            )

            data class State(
                val id: String,
                val name: String
            )
        }

        data class State(
            val id: String,
            val name: String?
        )
    }

    data class Shipping(
        val dimensions: Any?,
        @SerializedName("free_methods")
        val freeMethods: List<FreeMethod>,
        @SerializedName("free_shipping")
        val freeShipping: Boolean,
        @SerializedName("local_pick_up")
        val localPickUp: Boolean,
        @SerializedName("logistic_type")
        val logisticType: String,
        val mode: String,
        @SerializedName("store_pick_up")
        val storePickUp: Boolean,
        val tags: List<String>
    ) {
        data class FreeMethod(
            val id: Float,
            val rule: Rule
        ) {
            data class Rule(
                val default: Boolean,
                @SerializedName("free_mode")
                val freeMode: String,
                @SerializedName("free_shipping_flag")
                val freeShippingFlag: Boolean,
                val value: Any?
            )
        }
    }

    data class Variation(
        @SerializedName("attribute_combinations")
        val attributeCombinations: List<Any>,
        @SerializedName("available_quantity")
        val availableQuantity: Float,
        @SerializedName("catalog_product_id")
        val catalogProductId: Any?,
        val id: Long,
        @SerializedName("picture_ids")
        val pictureIds: List<String>,
        val price: Float,
        @SerializedName("sale_terms")
        val saleTerms: List<Any>,
        @SerializedName("sold_quantity")
        val soldQuantity: Float
    )
}