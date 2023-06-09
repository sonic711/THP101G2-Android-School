package com.example.thp101g2_android_school.firm.model

import android.graphics.Bitmap
import java.io.Serializable

data class FirmProduct(
    var imageId: Int? = null,
    var shopProductId:String? = null,
    var shopProductName: String? = null,
    var shopProductPrice: String? = null,
    var shopProductDesc: String? = null,
    var shopProductStatus :Int? = null,
    var shopProductClass : String? = null,
    var shopProductSearch : String? = null,
    var shopName:String? = null,
    var shopProductCount:String? = null,
    var shopProductImgBase64: String? = null,// Base64
    var shopProductImg:ByteArray? = null,
    var firmNo:String? = null
    ):
    Serializable