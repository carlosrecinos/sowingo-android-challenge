package com.recinos.sowingo_android_challenge.main.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiHelper {
    // This could be dynamically allocated to use different environments
    var BASE_URL = "https://demo5514996.mockable.io/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}