package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.manage.model.Firms

class ManageFirmViewModel : ViewModel() {
val firmo: MutableLiveData<Firms> by lazy { MutableLiveData<Firms>() }
}