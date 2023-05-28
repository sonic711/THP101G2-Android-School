package com.example.thp101g2_android_school.shop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.shop.model.ShopOrderList

class ShopOrderViewModel : ViewModel() {
    private var orderList = mutableListOf<ShopOrderList>()
    val orders : MutableLiveData<List<ShopOrderList>>by lazy {MutableLiveData<List<ShopOrderList>>()}

    init {
        loadProduct()
    }
    fun search(newText: String?) {
        if (newText.isNullOrEmpty()) {
            orders.value = orderList
        } else {
            // 如果不是空字串，宣告新的集合，走訪每個元素的name屬性，如果符合就放到新的集合並指派
            val searchLessionList = mutableListOf<ShopOrderList>()
            orderList.forEach { order ->
                if (order.name.contains(newText, true)) {
                    searchLessionList.add(order)
                }
            }
            // 指派給LiveData 就可以即時刷新View
            orders.value = searchLessionList
        }
    }

    private fun loadProduct() {
        val orderList = mutableListOf<ShopOrderList>()
        orderList.add(ShopOrderList(R.drawable.java, "java基礎教科書", "21856trsehse1221","2023/5/22", price = 500.0.toString(),"Willian","超級蘋果人","0905231318","台北市松山區20號"))


        this.orderList = orderList
        this.orders.value = this.orderList
    }
}