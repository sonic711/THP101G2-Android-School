package com.example.thp101g2_android_school.firm.viewmodel

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.firm.model.Order

class FirmDataCenterViewModel : ViewModel() {
    val firmSaleData : MutableLiveData<Order> by lazy { MutableLiveData<Order>() }

}