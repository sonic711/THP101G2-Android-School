package com.example.thp101g2_android_school.member.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterVerificationViewModel : ViewModel() {
    val phone: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}