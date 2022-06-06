package com.recinos.sowingo_android_challenge.main.view

import com.recinos.sowingo_android_challenge.main.model.ui.ProductCardViewModel

interface OnClickListener {
    fun onClick(product: ProductCardViewModel, i: Int)
}