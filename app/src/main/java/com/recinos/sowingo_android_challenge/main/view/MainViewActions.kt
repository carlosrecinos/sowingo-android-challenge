package com.recinos.sowingo_android_challenge.main.view

import com.recinos.sowingo_android_challenge.main.model.Product
import com.recinos.sowingo_android_challenge.main.model.ui.ProductCardViewModel

sealed class MainViewActions
data class ShowProducts(val data: List<ProductCardViewModel>): MainViewActions()
data class ShowError(val message : String?): MainViewActions()
object ShowLoading : MainViewActions()
