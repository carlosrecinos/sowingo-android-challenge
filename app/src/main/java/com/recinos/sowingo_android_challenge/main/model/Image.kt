package com.recinos.sowingo_android_challenge.main.model

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("image_240_wide") var image240Wide: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("image_240_box") var image240Box: String? = null,
    @SerializedName("main_image_bool") var mainImageBool: Boolean? = null
)
