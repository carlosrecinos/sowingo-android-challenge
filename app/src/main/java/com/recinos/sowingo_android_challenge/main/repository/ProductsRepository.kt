package com.recinos.sowingo_android_challenge.main.repository

import androidx.lifecycle.MutableLiveData
import com.recinos.sowingo_android_challenge.main.ApiResponse
import com.recinos.sowingo_android_challenge.main.api.ApiInterface
import com.recinos.sowingo_android_challenge.main.model.Product
import com.recinos.sowingo_android_challenge.main.utils.isOnline
import kotlinx.coroutines.GlobalScope
import retrofit2.awaitResponse

interface IProductsRepository {
    fun getProducts()
}

class ProductsRepository(
    val api: ApiInterface = ApiInterface.create()
): IProductsRepository {
    var response: MutableLiveData<ApiResponse<Product>> = MutableLiveData()

    override fun getProducts() {
        response.value = ApiResponse.Loading()
        val isOnline = true
        if(isOnline) {
            try{
                GlobalScope.dis
            }
            catch(e: Exception){
                response.value = NetworkResponse.Failure(e.message())
            }

        }
        else{
            response.value = NetworkResponse.Failure("No Internet connection")
        }
    }
}