package com.example.thp101g2_android_school.member.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.JsonObject

class RegisterViewModel : ViewModel() {
    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>(Member()) }
    val phoneNumber: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val cPassword: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val verificationCode: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val countdown: MutableLiveData<String> by lazy { MutableLiveData<String>() }

}