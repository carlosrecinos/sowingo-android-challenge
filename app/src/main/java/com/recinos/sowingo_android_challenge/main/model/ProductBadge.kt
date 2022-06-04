package com.recinos.sowingo_android_challenge.main.model

import com.google.gson.annotations.SerializedName

data class ProductBadge(
    @SerializedName("badge_type") var badgeType: String? = null,
    @SerializedName("badge_image_url") var badgeImageUrl: String? = null
)
