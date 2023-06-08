package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.manage.model.SelectMemberBean
import com.example.thp101g2_android_school.manage.model.TeaApplyBean

class ManageTeaApplyViewModel : ViewModel() {
    val teaapply: MutableLiveData<TeaApplyBean> by lazy { MutableLiveData<TeaApplyBean>() }
}