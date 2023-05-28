package com.example.thp101g2_android_school.shop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.shop.model.Product
import com.example.thp101g2_android_school.shop.model.ShopingCart

class ShopingCartViewModel : ViewModel() {
    val cartproduct: MutableLiveData<ShopingCart> by lazy { MutableLiveData<ShopingCart>() }
}