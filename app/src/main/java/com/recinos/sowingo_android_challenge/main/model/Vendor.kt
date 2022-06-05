package com.recinos.sowingo_android_challenge.main.model

import com.google.gson.annotations.SerializedName

data class Vendor(
    @SerializedName("image") var image: String? = null,
    @SerializedName("is_active") var isActive: Boolean? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null

)
