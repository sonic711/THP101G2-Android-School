package com.example.thp101g2_android_school.firm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.firm.model.Firm

class FirmShopSettingViewModel :ViewModel() {
    val firm : MutableLiveData<Firm> by lazy { MutableLiveData<Firm>() }

    // 待改
//    private val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/ordermanager/1"
//
//    fun init(){
//        firm.value = requestTask(url,"OPTIONS")
//    }


}