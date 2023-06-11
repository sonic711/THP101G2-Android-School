package com.example.thp101g2_android_school.firm.model

import android.graphics.Bitmap
import java.io.Serializable

data class Firm(
    var firmNo:Int? = null,
    var userId:String? = null,
    var password:String = "",
    var shopName:String? = null,
    var phoneNumber:String? = null,
    var firmEmail:String? = null,
    var profilePhoto: ByteArray? = null,
    var coverPhoto:ByteArray? = null,
    var firmStatus:Int? = null,
    var createdAt:String? = null,
    var shopInfo:String? = null,
    var profilePhoto64:String? = null,
    var coverPhoto64:String? = null
    ):
    Serializable