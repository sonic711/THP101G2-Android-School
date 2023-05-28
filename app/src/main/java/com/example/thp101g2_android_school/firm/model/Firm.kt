package com.example.thp101g2_android_school.firm.model

import android.graphics.Bitmap
import java.io.Serializable

class Firm(
    var firmNo:Int,
    var userId:Int,
    var password:String,
    var shopName:String,
    var phoneNumber:String,
    var FirmEmail:String,
    var profilePhoto: Bitmap,
    var coverPhoto:Bitmap,
    var firmStatus:Int,
    var createdAt:String,
    var shopInfo:String
    ):
    Serializable