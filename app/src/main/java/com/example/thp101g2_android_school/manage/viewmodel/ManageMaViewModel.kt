package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.manage.model.Mas

class ManageMaViewModel : ViewModel() {
    val mao: MutableLiveData<Mas> by lazy { MutableLiveData<Mas>()  }
}