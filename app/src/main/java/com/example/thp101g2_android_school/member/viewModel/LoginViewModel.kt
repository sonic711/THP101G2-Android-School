package com.example.thp101g2_android_school.member.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.member.model.Member

class LoginViewModel : ViewModel() {
    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>(Member()) }
//    val firm: MutableLiveData<Firm> by lazy { MutableLiveData<Firm>(
//        Firm(-1, "", "", "", "", "",
//            null, null,null, null, -1, "", "")) }
}