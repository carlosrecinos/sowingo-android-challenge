package com.recinos.sowingo_android_challenge.main.api

import com.recinos.sowingo_android_challenge.main.model.responses.FavoriteResponse
import com.recinos.sowingo_android_challenge.main.model.responses.ProductsResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface IProductsApi {
    @GET("products")
    suspend fun getProducts() : Response<ProductsResponse>

    @POST("favorites")
    suspend fun setFavorite() : Response<FavoriteResponse>

    @DELETE("favorites")
    suspend fun deleteFavorite() : Response<FavoriteResponse>
}