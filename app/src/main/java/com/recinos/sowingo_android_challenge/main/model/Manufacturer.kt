package com.recinos.sowingo_android_challenge.main.model

import com.google.gson.annotations.SerializedName

data class Manufacturer (
    @SerializedName("sku"  ) var sku  : String? = null,
    @SerializedName("id"   ) var id   : String? = null,
    @SerializedName("name" ) var name : String? = null

)
