package com.example.thp101g2_android_school.shop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.shop.model.Product

class ShopFrontViewModel : ViewModel() {
    val product: MutableLiveData<Product> by lazy { MutableLiveData<Product>() }
}