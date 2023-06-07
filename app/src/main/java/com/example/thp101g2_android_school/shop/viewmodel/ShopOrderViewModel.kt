package com.example.thp101g2_android_school.shop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.shop.model.Product
import com.example.thp101g2_android_school.shop.model.ShopOrderList
import com.google.gson.reflect.TypeToken

class ShopOrderViewModel : ViewModel() {
    private var orderList = mutableListOf<ShopOrderList>()
    val orders : MutableLiveData<List<ShopOrderList>>by lazy {MutableLiveData<List<ShopOrderList>>()}

    init {
        loadOrderProduct()
    }
    fun search(newText: String?) {
        if (newText.isNullOrEmpty()) {
            orders.value = orderList
        } else {
            // 如果不是空字串，宣告新的集合，走訪每個元素的name屬性，如果符合就放到新的集合並指派
            val searchLessionList = mutableListOf<ShopOrderList>()
            orderList.forEach { order ->
                if (order.shopProductName.contains(newText, true)) {
                    searchLessionList.add(order)
                }
            }
            // 指派給LiveData 就可以即時刷新View
            orders.value = searchLessionList
        }
    }

    fun loadOrderProduct() {
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/shop/buy"
        val type = object : TypeToken<List<ShopOrderList>>() {}.type
        val list = requestTask<List<ShopOrderList>>(url, respBodyType = type) ?: return
        val shopOrderList = mutableListOf<ShopOrderList>()

        for (order in list!!) {
            shopOrderList.add(order)
        }


        this.orderList = shopOrderList
        this.orders.value = this.orderList
        println(orders)
    }
}