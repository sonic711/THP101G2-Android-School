package com.example.thp101g2_android_school.firm.model

import java.io.Serializable

class Order (var orderId : String,
             var orderTime : String,
             var buyer : String ,
             var adress :String,
             var imageId : Int,
             var productName : String,
             var productPrice : String,
             var count : Int,
             var sum : String):
    Serializable