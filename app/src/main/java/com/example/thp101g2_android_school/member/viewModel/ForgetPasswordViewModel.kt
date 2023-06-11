package com.example.thp101g2_android_school.member.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ForgetPasswordViewModel : ViewModel() {
    val email: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val phoneNumber: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val verificationCode: MutableLiveData<String> by lazy { MutableLiveData<String>() }

}