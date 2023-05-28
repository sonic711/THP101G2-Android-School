package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.manage.model.Reports

class ManageReportViewModel : ViewModel() {
    val reporto: MutableLiveData<Reports> by lazy { MutableLiveData<Reports>()  }
}