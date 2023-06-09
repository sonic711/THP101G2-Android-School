package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.manage.model.Members
import com.example.thp101g2_android_school.manage.model.SelectMemberBean
import com.example.thp101g2_android_school.manage.model.TeaApplyBean

class ManageMemberViewModel : ViewModel() {
    val memberuse: MutableLiveData<SelectMemberBean> by lazy {MutableLiveData<SelectMemberBean>() }


}