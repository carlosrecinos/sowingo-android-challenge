package com.recinos.sowingo_android_challenge.main.model

import com.google.gson.annotations.SerializedName

data class Promotion(
    @SerializedName("promotion_properties") var promotionProperties: PromotionProperty? = PromotionProperty(),
    @SerializedName("effective_price") var effectivePrice: Double? = null,
    @SerializedName("end_date") var endDate: String? = null,
    @SerializedName("promotion_notes") var promotionNotes: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("promotion_type") var promotionType: String? = null,
    @SerializedName("start_date") var startDate: String? = null

)
