package com.example.thp101g2_android_school.firm.model

import android.graphics.Bitmap
import java.io.Serializable

class Order (
    var shopOrderId : String? = null,
    var shopProductId:String? = null,
    var memberNo:String? = null,
    var shopAdress :String? = null,
    var shopRecipient : String ? = null,
    var shopOrderCount : String? = null,
    var shopOrderStatus:String? = null,
    var shopOrderpayTime:String? = null,
    var shopOrdercreateTime : String? = null,
//    var shopOrderDescId :String? = null,
    var shopProductName : String? = null,
    var shopProductPrice : String? = null,
    var shopProductSales : String? = null,
    var shopProductImg: ByteArray? = null
):
    Serializable