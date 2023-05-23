package com.example.thp101g2_android_school.firm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.firm.model.Order

class FirmOrderViewModel :ViewModel() {
    val firmOrder : MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
}