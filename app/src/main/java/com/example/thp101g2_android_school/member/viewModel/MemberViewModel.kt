package com.example.thp101g2_android_school.member.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.member.model.Member
import kotlinx.coroutines.launch

class MemberViewModel: ViewModel() {
    val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/members"
    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>(Member()) }

    fun initialize(){
        viewModelScope.launch {
            loadData()
        }
    }

    private suspend fun loadData() {
        member.value = requestTask(url, "OPTIONS")
    }
}