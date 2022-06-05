package com.recinos.sowingo_android_challenge.main.view

import com.recinos.sowingo_android_challenge.main.model.Product

sealed class MainViewActions {
    class ShowProducts(data : ArrayList<Product>): MainViewActions()
    class ShowError(message : String?): MainViewActions()
    object ShowLoading : MainViewActions()
}


