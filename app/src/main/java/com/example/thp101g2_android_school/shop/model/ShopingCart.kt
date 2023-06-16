package com.example.thp101g2_android_school.shop.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
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
    Serializable, Parcelable
class ShopPriceResult(var totalPriceResult: Int = 0):
    Serializable
//data class PointSelect (
//    var rewardPoints:Int ):
//    Serializable