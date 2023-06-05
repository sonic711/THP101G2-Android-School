package com.example.thp101g2_android_school.firm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.firm.model.FirmProduct
import com.example.thp101g2_android_school.firm.model.Order
import com.google.gson.reflect.TypeToken

class FirmOrdersViewModel:ViewModel() {
    // STEP02.建立兩個集合，LiveData的是給搜尋時更新用，一般的MutableList是給資料庫來的所有資料用
    // 原始訂單列表
    // 專門存取要顯示的畫面，受監控的LiveData，一旦指派新值就會更新訂單列表畫面
    private var orderList = mutableListOf<Order>()

    // 受監控的LiveData，一旦指派新值就會更新訂單列表畫面
    val orders: MutableLiveData<List<Order>> by lazy { MutableLiveData<List<Order>>() }

    // 載入資料
    init {//初始化，這邊假設load遠端資料
        loadOrders()
    }

    private fun loadOrders() {
        //val firmId = 3
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/ordermanager/firm"//$firmId
        val type = object : TypeToken<List<Order>>() {}.type
        val list = requestTask<List<Order>>(url, respBodyType = type)

        for(item in list!!){
            orderList.add(item)
        }

        this.orderList = orderList
        this.orders.value = this.orderList
    }


    fun orderAll(){
        val orderAllList = mutableListOf<Order>()
        for (o in orderList){
            orderAllList.add(o)
        }
        this.orders.value =  orderAllList
    }

    fun orderNotShip(){
        val orderNotShipList = mutableListOf<Order>()
        for (o in orderList){
            if (o.shopOrderStatus?.toInt() == 1){
                orderNotShipList.add(o)
            }
        }
        this.orders.value =  orderNotShipList
    }

    fun orderShipped(){
        val orderShippedList = mutableListOf<Order>()
        for (o in orderList){
            if (o.shopOrderStatus?.toInt() == 0){
                orderShippedList.add(o)
            }
        }
        this.orders.value =  orderShippedList
    }

}