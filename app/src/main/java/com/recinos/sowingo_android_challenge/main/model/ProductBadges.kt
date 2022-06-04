package com.recinos.sowingo_android_challenge.main.model

import com.google.gson.annotations.SerializedName

data class ProductBadges(
    @SerializedName("has_badge") var hasBadge: Boolean? = null,
    @SerializedName("badges") var badges: ArrayList<ProductBadge> = arrayListOf()

)