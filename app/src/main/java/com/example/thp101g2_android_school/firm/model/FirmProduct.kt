package com.example.thp101g2_android_school.firm.model

import java.io.Serializable

class FirmProduct(
    var imageId: Int,
    var name: String,
    var author: String,
    var price: String,
    var pinfo: String,
    var status :Int,
    var publish : String,
    var publishTime : String,
    var productClass : String,
    var productTag : String):
    Serializable