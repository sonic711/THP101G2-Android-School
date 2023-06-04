package com.example.thp101g2_android_school.member.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.member.model.Follower
import com.example.thp101g2_android_school.member.model.Member
import kotlinx.coroutines.launch

class MemberViewModel: ViewModel() {
    val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/members"
    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>(Member()) }

    val follower: MutableLiveData<Follower> by lazy { MutableLiveData<Follower>(Follower()) }
    val followers: MutableLiveData<MutableList<Follower>> by lazy { MutableLiveData<MutableList<Follower>>() }
    val countFollowers: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    val fan: MutableLiveData<Follower> by lazy { MutableLiveData<Follower>(Follower()) }
    val fans: MutableLiveData<MutableList<Follower>> by lazy { MutableLiveData<MutableList<Follower>>() }
    val countFans: MutableLiveData<String> by lazy { MutableLiveData<String>() }


    private var followerList = mutableListOf<Follower>() // 裝全部 followers
    fun initialize(){
        viewModelScope.launch {
            member.value = requestTask(url, "OPTIONS")
            loadFollowers()
            loadFans()
        }
    }

    private fun loadFollowers() {
        val urlFollow = "http://10.0.2.2:8080/THP101G2-WebServer-School/member/follow/followers"
        val respBody = requestTask<MutableList<Follower>>("$urlFollow/${member.value!!.memberNo}")
        countFollowers.postValue(respBody?.size.toString())
        followers.value = respBody
    }
    private fun loadFans() {
        val urlFans = "http://10.0.2.2:8080/THP101G2-WebServer-School/member/follow/fans"
        val respBody = requestTask<MutableList<Follower>>("$urlFans/${member.value!!.memberNo}")
        countFans.postValue(respBody?.size.toString())
        fans.value = respBody
    }

}