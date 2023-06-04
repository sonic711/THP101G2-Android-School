package com.example.thp101g2_android_school.member.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.course.model.Courses
import com.example.thp101g2_android_school.member.model.Follower
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.reflect.TypeToken
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
    private var fanList = mutableListOf<Follower>() // 裝全部 fans
    fun initialize(){
        viewModelScope.launch {
            member.value = requestTask(url, "OPTIONS")
            loadFollowers()
            loadFans()
        }
    }

    private fun loadFollowers() {
        val urlFollow = "http://10.0.2.2:8080/THP101G2-WebServer-School/member/follow/followers/${member.value!!.memberNo}"
        val type = object : TypeToken<MutableList<Follower>>() {}.type
        val list = requestTask<MutableList<Follower>>(urlFollow, respBodyType = type)
        countFollowers.postValue(list?.size.toString())
        followers.value = list
        followers.value?.let {
            followerList = it
        }
    }
    private fun loadFans() {
        val urlFans = "http://10.0.2.2:8080/THP101G2-WebServer-School/member/follow/fans/${member.value!!.memberNo}"
        val type = object : TypeToken<MutableList<Follower>>() {}.type
        val list = requestTask<MutableList<Follower>>(urlFans, respBodyType = type)
        countFans.postValue(list?.size.toString())
        fans.value = list
    }

    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            followers.value = followerList
        } else {
            val searchList = mutableListOf<Follower>()
            followerList.forEach { follower ->
                if (follower.userId.contains(newText, true)) {
                    searchList.add(follower)
                }
            }
            followers.value = searchList
        }
    }

}