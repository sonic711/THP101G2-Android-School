package com.example.thp101g2_android_school.shop.model

import androidx.fragment.app.Fragment
import java.io.Serializable

class ShopOrderList (var shopOrderImage: ByteArray,
                     var shopProductName: String,
                     var shopOrderId: String,
                     var shopOrdercreateTime: String,
                     var shopProductSales: String,
                     var shopName: String,
                     var shopRecipient: String,
                     var shopOrderPhone: String,
                     var shopAddress: String
                     ):
    Serializable