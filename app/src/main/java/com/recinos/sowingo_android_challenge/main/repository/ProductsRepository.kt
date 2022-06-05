package com.recinos.sowingo_android_challenge.main.repository

import com.recinos.sowingo_android_challenge.main.ApiResponse
import com.recinos.sowingo_android_challenge.main.api.IProductsApi
import com.recinos.sowingo_android_challenge.main.model.Product
import com.recinos.sowingo_android_challenge.main.model.responses.FavoriteResponse
import com.recinos.sowingo_android_challenge.main.model.responses.ProductsResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductsRepository: BaseRepository(), KoinComponent {
    private val api: IProductsApi by inject()

    suspend fun getProducts(): ApiResponse<ProductsResponse> {
        return safeApiCall { api.getProducts() }
    }

    suspend fun setFavorite(product: Product): ApiResponse<FavoriteResponse> {
        return safeApiCall { api.setFavorite(product) }
    }

    suspend fun deleteFavorite(product: Product): ApiResponse<FavoriteResponse> {
        return safeApiCall { api.deleteFavorite(product) }
    }
}