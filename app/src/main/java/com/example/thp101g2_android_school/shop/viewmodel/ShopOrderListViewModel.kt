package com.example.thp101g2_android_school.shop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.shop.model.ShopOrderList

class ShopOrderListViewModel : ViewModel() {
    val order: MutableLiveData<ShopOrderList> by lazy { MutableLiveData<ShopOrderList>() }
}