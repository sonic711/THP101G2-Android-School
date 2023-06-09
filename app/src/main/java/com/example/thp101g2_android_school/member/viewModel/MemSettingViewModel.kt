package com.example.thp101g2_android_school.member.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.member.model.Member
import kotlinx.coroutines.launch

class MemSettingViewModel : ViewModel() {
    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>(Member()) }

    val oPassword: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val nPassword: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val cPassword: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    val reason: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun initialize(){
        viewModelScope.launch {
            member.value = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
        }
    }
}