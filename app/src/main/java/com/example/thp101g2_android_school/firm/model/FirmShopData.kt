package com.example.thp101g2_android_school.firm.model

import java.io.Serializable

class FirmShopData(
    var shopName:String? = null,
    var profilePhoto: ByteArray? = null,
    var coverPhoto:ByteArray? = null,
    var shopInfo:String? = null,
): Serializable