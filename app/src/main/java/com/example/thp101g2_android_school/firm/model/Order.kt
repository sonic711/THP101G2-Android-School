package com.example.thp101g2_android_school.firm.model

import java.io.Serializable

class Order (
    var imageId : Int,
    var shopOrderId : String,
    var shopProductId:String,
    var memberNo:String,
    var shopAdress :String,
    var shopRecipient : String ,
    var shopOrderCount : String,
    var shopOrderStatus:String,
    var shopOrderpayTime:String,
    var shopOrdercreateTime : String,
//    var shopOrderDescId :String,
    var shopProductName : String,
    var shopProductPrice : String,
    var shopProductSales : String, ):
    Serializable