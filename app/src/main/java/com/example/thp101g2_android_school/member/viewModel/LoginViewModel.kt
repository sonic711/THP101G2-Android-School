package com.example.thp101g2_android_school.member.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.firm.model.Firm
import com.example.thp101g2_android_school.manage.model.Manager
import com.example.thp101g2_android_school.member.model.Member

class LoginViewModel : ViewModel() {
    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>(Member()) }
    val firm: MutableLiveData<Firm> by lazy { MutableLiveData<Firm>(Firm()) }
    val manager: MutableLiveData<Manager> by lazy { MutableLiveData<Manager>() }
    val manageAc: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val managePass: MutableLiveData<String> by lazy { MutableLiveData<String>() }

}