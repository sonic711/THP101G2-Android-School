package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.manage.model.Members

class ManageMemberViewModel : ViewModel() {
    val memberuse: MutableLiveData<Members> by lazy {MutableLiveData<Members>() }
}