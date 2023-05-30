package com.example.thp101g2_android_school.shop.model

import java.io.Serializable

class Product(
    var shopProductId: String,
    var imageId: Int,
    var shopProductName: String,
    var shopName: String,
    var shopProductPrice: String,
    var shopProductDesc: String,
    var shopProductCount: String)
    : Serializable