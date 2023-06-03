package com.example.thp101g2_android_school.shop.model

import java.io.Serializable

class ShopingCart( var imageId: Int,
                   var shoppingCartId:String,
                   var shopProductId: String,
                   var shopProductName: String,
                   var shopProductPrice:String,
                   var shopName:String,
                   var rewardPoints: String,
                   var shopProductCount: Int
                   ):
    Serializable