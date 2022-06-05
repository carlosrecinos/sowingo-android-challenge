package com.recinos.sowingo_android_challenge.main.api

import com.recinos.sowingo_android_challenge.main.model.Product
import com.recinos.sowingo_android_challenge.main.model.responses.FavoriteResponse
import com.recinos.sowingo_android_challenge.main.model.responses.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface IProductsApi {
    @GET("products")
    suspend fun getProducts() : Response<ProductsResponse>

    @GET("favorites")
    suspend fun setFavorite(product: Product) : Response<FavoriteResponse>

    @GET("favorites")
    suspend fun deleteFavorite(product: Product) : Response<FavoriteResponse>
}