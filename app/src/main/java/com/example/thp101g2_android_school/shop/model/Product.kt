package com.example.thp101g2_android_school.shop.model

import java.io.Serializable

class Product(
    var shopProductImage: ByteArray,
    var shopProductId: String,
    var shopProductName: String,
    var shopProductPrice: String,
    var shopName: String,
    var shopProductDesc: String,
    var shopProductCount: Int,
    var memberNo: Int,
    var rewardPoints:Int,
    var firmNo:Int
    )
    : Serializable {
        fun toShopFavorite(): ShopFavorite {
            return ShopFavorite(
                shopProductImage, shopProductId, shopProductName, shopProductPrice, shopName, memberNo
            )
        }
        fun toShoppingCart():ShopingCart{
            return ShopingCart(
                shopProductImage, shopProductId, shopProductName, shopProductPrice, shopName,shopProductCount,rewardPoints,memberNo,firmNo
            )
        }
    }