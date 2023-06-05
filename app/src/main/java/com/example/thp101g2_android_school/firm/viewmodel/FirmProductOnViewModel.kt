package com.example.thp101g2_android_school.firm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.firm.model.FirmProduct

class FirmProductOnViewModel : ViewModel() {
    val productOn : MutableLiveData<FirmProduct> by lazy { MutableLiveData<FirmProduct>() }


}