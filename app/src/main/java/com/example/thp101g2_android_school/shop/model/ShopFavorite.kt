package com.example.thp101g2_android_school.shop.model

import java.io.Serializable
data class ShopFavorite(
    var imageId: Int,
    var shopProductId: String,
    var shopProductName: String,
    var shopProductPrice:String,
    var shopName:String):
    Serializable