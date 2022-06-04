package com.recinos.sowingo_android_challenge.main.model

import com.google.gson.annotations.SerializedName


data class Category (
    @SerializedName("id") var id   : String? = null,
    @SerializedName("name") var name : String? = null

)
