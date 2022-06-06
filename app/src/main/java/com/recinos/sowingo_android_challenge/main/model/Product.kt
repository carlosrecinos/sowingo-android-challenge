package com.recinos.sowingo_android_challenge.main.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") var id: String,
    @SerializedName("unit_name") var unitName: String? = null,
    @SerializedName("subcategory") var subcategory: Category? = Category(),
    @SerializedName("objectID") var objectID: String? = null,
    @SerializedName("medical_field_id") var medicalFieldId: String? = null,
    @SerializedName("content_per_unit") var contentPerUnit: String? = null,
    @SerializedName("filters") var filters: Map<String, String> = mapOf(),
    @SerializedName("images") var images: ArrayList<Image> = arrayListOf(),
    @SerializedName("on_hand") var onHand: Int? = null,
    @SerializedName("main_image_240_box") var mainImage240Box: String? = null,
    @SerializedName("barcodes") var barcodes: String? = null,
    @SerializedName("vendor_inventory") var vendorInventory: ArrayList<VendorInventory> = arrayListOf(),
    @SerializedName("country_id") var countryId: String? = null,
    @SerializedName("tracking_method") var trackingMethod: String? = null,
    @SerializedName("is_favourite_product") var isFavouriteProduct: Boolean? = null,
    @SerializedName("advertising_badges") var advertisingBadges: ProductBadges? = ProductBadges(),
    @SerializedName("order_package_quantity") var orderPackageQuantity: Int? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("marketplace_id") var marketplaceId: String? = null,
    @SerializedName("parent_category") var parentCategory: Category? = Category(),
    @SerializedName("buy_by_case") var buyByCase: Boolean? = null,
    @SerializedName("minimum_level") var minimumLevel: String? = null,
    @SerializedName("manufacturer_sku") var manufacturerSku: String? = null,
    @SerializedName("manufacturer") var manufacturer: Manufacturer? = Manufacturer(),
    @SerializedName("main_image") var mainImage: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("sds_url") var sdsUrl: ArrayList<String> = arrayListOf(),
    @SerializedName("office_inventory_item_id") var officeInventoryItemId: String? = null,
    @SerializedName("item_type") var itemType: String? = null,
    @SerializedName("main_image_240_wide") var mainImage240Wide: String? = null
)