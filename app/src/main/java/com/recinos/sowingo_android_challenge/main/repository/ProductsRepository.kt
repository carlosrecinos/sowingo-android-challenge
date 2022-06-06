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

    suspend fun setFavorite(): ApiResponse<FavoriteResponse> {
        return safeApiCall { api.setFavorite() }
    }

    suspend fun deleteFavorite(): ApiResponse<FavoriteResponse> {
        return safeApiCall { api.deleteFavorite() }
    }
}