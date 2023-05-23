package com.example.thp101g2_android_school.firm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.firm.model.Order

class FirmOrdersViewModel:ViewModel() {
    // STEP02.建立兩個集合，LiveData的是給搜尋時更新用，一般的MutableList是給資料庫來的所有資料用
    // 原始課程列表
    // 專門存取要顯示的畫面，受監控的LiveData，一旦指派新值就會更新課程列表畫面
    private var orderList = mutableListOf<Order>()

    // 受監控的LiveData，一旦指派新值就會更新課程列表畫面
    val orders: MutableLiveData<List<Order>> by lazy { MutableLiveData<List<Order>>() }

    // 載入資料
    init {//初始化，這邊假設load遠端資料
        loadOrders()
    }

    private fun loadOrders() {
        val orderList = mutableListOf<Order>()
        orderList.add(Order("32234234","2023-11-2 下午2:33","黃彬華","新北市新莊區",
            R.drawable.kotlin,"Kotlin","200",2,"400"))
        orderList.add(Order("32234276","2023-1-9 下午5:25","李偉銘","桃園市龜山區",
            R.drawable.java,"Java","200",1,"200"))
        orderList.add(Order("25234257","2023-4-3 下午7:35","先輩","日本秋葉原",
            R.drawable.python,"Python","300",2,"600"))
        orderList.add(Order("87234257","2023-5-9 下午9:39","前輩","日本東京",
            R.drawable.senpai,"Senpai","150",3,"450"))
        orderList.add(Order("32234234","2023-11-2 下午2:33","黃彬華","新北市新莊區",
            R.drawable.kotlin,"Kotlin","200",2,"400"))
        orderList.add(Order("32234276","2023-1-9 下午5:25","李偉銘","桃園市龜山區",
            R.drawable.java,"Java","200",1,"200"))
        orderList.add(Order("25234257","2023-4-3 下午7:35","先輩","日本秋葉原",
            R.drawable.python,"Python","300",2,"600"))
        orderList.add(Order("87234257","2023-5-9 下午9:39","前輩","日本東京",
            R.drawable.senpai,"Senpai","150",3,"450"))

        this.orderList = orderList
        this.orders.value = this.orderList
    }
}