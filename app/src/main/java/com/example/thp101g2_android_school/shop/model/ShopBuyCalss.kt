package com.example.thp101g2_android_school.shop.model

import java.sql.Timestamp

data class ShopBuyCalss(
    var shopProductId: Int? = null,
    var shopOrderId: Int? = null,
    var shopProductName: String? = null,
    var shopAddress: String? = null,
    var shopRecipient: String? = null,
    var shopProductPrice: Int? = null,
    var shopOrderPhone: String? = null,
    var shopProductSearch: String? = null,
    var shopProductClass: String? = null,
    var shopPointDisCount: Int? = null,
    var shopOrderStatus: Int? = null,
    var shopProductDesc: String? = null,
    var shopProductStatus: Int? = null,
    var shopProductCount: Int? = null,
    var shopName: String? = null,
    var firmNo: Int? = null,
    var memberNo: Int? = null,
    var rewardPoints: Int? = null,
    var shopPointDiscount: Int? = null,
    var shopOrdercreateTime: Timestamp? = null,
    var shopOrderCount: Int? = null,
    var shopFavoriteId: Int? = null,
    var shopProductSales: Int? = null,
    var shopProductImage: ByteArray? = null,
    var shopOrderImage: ByteArray? = null
)
