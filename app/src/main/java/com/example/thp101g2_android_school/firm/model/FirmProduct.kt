package com.example.thp101g2_android_school.firm.model

import android.graphics.Bitmap
import java.io.Serializable

class FirmProduct(
    var imageId: Int,
    var shopProductName: String? = null,
    var shopProductPrice: String? = null,
    var shopProductDesc: String? = null,
    var shopProductStatus :Int? = null,
    var shopProductClass : String? = null,
    var shopProductSearch : String? = null,
    var shopName:String? = null,
    var shopProductCount:String? = null,
    var shopProductImgBase64: String? = null,
    var shopProductImg:ByteArray? = null,
    ):
    Serializable