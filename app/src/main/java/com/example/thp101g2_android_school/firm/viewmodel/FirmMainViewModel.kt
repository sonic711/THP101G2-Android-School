package com.example.thp101g2_android_school.firm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.firm.model.FirmProduct

/**
 * 單一商品資料處理
 */
class FirmMainViewModel : ViewModel() {
    val firmProduct : MutableLiveData<FirmProduct> by lazy { MutableLiveData<FirmProduct>() }
}