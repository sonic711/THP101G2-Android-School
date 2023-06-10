package com.example.thp101g2_android_school.shop.model

import java.io.Serializable

class ShopingCart( var shopProductImage: ByteArray,
                   var shopProductId: String,
                   var shopProductName: String,
                   var shopProductPrice:String,
                   var shopName:String,
                   var shopProductCount:Int,
                   var rewardPoints:Int,
                   var memberNo: Int,
                   var firmNo: Int
                   ):
    Serializable
//data class PointSelect (
//    var rewardPoints:Int ):
//    Serializable