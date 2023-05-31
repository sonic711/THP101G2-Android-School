package com.example.thp101g2_android_school.firm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.firm.model.Order
import com.google.gson.reflect.TypeToken

class FirmDatasCenterViewModel :ViewModel() {

    private  var dataCenterList = mutableListOf<Order>()

    val datas: MutableLiveData<List<Order>> by lazy { MutableLiveData<List<Order>>() }

    init {//初始化，這邊假設load遠端資料
        loadDatas()
    }

    // 網址待更改
    private fun loadDatas() {
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/datacenter"
        val type = object : TypeToken<List<Order>>() {}.type
        val list = requestTask<List<Order>>(url, respBodyType = type) ?: return
        dataCenterList = list.toMutableList()
//        for(item in list!!){
//            dataCenterList.add(item)
//        }
        this.dataCenterList = dataCenterList
        this.datas.value = this.dataCenterList


    }

}