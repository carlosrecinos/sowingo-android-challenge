package com.recinos.sowingo_android_challenge.main.model.ui

data class ProductCardViewModel(
    val id: String?,
    val image: String?,
    val title: String?,
    val price: Double?,
    val listPrice: Double?,
    val isFavorite: Boolean?,
    val badgeImageUrl: String?
)
