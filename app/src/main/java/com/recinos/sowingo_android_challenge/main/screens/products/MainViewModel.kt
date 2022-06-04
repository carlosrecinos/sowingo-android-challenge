package com.recinos.sowingo_android_challenge

import androidx.lifecycle.ViewModel
import com.recinos.sowingo_android_challenge.main.repository.ProductsRepository

interface IMainViewModel {
    fun fetchProducts()
}

class MainViewModel(
    private val repository: ProductsRepository
): ViewModel(), IMainViewModel {
    override fun fetchProducts() {
        // Do api call
    }
}