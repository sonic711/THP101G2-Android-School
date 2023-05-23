package com.example.thp101g2_android_school.member.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.member.model.Member

class MemberViewModel: ViewModel() {
    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>() }
}