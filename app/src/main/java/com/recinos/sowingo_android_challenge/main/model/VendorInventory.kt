package com.recinos.sowingo_android_challenge.main.model

import com.google.gson.annotations.SerializedName

data class VendorInventory(

    @SerializedName("promotions") var promotions: String? = null,
    @SerializedName("vendor_inventory_id") var vendorInventoryId: String? = null,
    @SerializedName("list_price") var listPrice: Double? = null,
    @SerializedName("marketplace_id") var marketplaceId: String? = null,
    @SerializedName("price") var price: Double? = null,
    @SerializedName("special_fee") var specialFee: String? = null,
    @SerializedName("special_fee_amount") var specialFeeAmount: String? = null,
    @SerializedName("vendor_sku") var vendorSku: String? = null,
    @SerializedName("has_promotions") var hasPromotions: Boolean? = null,
    @SerializedName("is_published") var isPublished: Boolean? = null

)
