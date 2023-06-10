package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ComPostAuthViewModel: ViewModel(){
    val mobile: MutableLiveData<String> by lazy { MutableLiveData<String>("0975014979") }
    val verificationCode: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    // 一開始先隱藏填寫驗證碼版面
    val layoutVisible: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }
    val text: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}