package com.example.thp101g2_android_school.firm.model

import java.io.Serializable

class FirmProduct(
    var imageId: Int,
    var shopProductName: String,
//    var author: String,
    var img: ByteArray,
    var shopProductPrice: String,
    var shopProductDesc: String,
    var shopProductStatus :Int,
    var shopProductClass : String,
    var shopProductSearch : String,
    var shopName:String):
    Serializable