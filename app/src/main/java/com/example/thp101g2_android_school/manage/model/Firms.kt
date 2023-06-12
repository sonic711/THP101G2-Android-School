package com.example.thp101g2_android_school.manage.model

import java.io.Serializable

data class Firms(
//            var firmID :Int,
//            var Firmname: String,
//            var Firmphone: String,
    var shopProductId: String? = null,
    var shopProductName: String? = null,
    var shopProductPrice: String? = null,
    var shopProductSearch: String? = null,
    var shopProductClass: String? = null,
    var shopProductStatus: String? = null,
    var shopName: String? = null,
    var firmNo: String? = null,
    var firmStatus: String ? = null
): Serializable