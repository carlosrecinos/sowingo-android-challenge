package com.recinos.sowingo_android_challenge.main.di

import android.content.Context
import com.recinos.sowingo_android_challenge.main.api.ApiHelper
import com.recinos.sowingo_android_challenge.main.api.IProductsApi
import com.recinos.sowingo_android_challenge.main.repository.ProductsRepository
import com.recinos.sowingo_android_challenge.main.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

data class ApplicationModule(val context: Context) {
    val appModule: Module = module {
        val productsViewModel = MainViewModel(context = context)
        single {
            ApiHelper.getInstance().create(IProductsApi::class.java)
        }
        single {
            ProductsRepository()
        }
        viewModel {
            productsViewModel
        }
    }
}