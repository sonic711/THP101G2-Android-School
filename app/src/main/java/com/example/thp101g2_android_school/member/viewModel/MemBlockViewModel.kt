package com.example.thp101g2_android_school.member.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.member.model.BlockedUser
import com.example.thp101g2_android_school.member.model.Follower
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class MemBlockViewModel : ViewModel() {
    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>(Member()) }
    val blockedUsers: MutableLiveData<MutableList<BlockedUser>> by lazy { MutableLiveData<MutableList<BlockedUser>>() }
    val blockedUser: MutableLiveData<BlockedUser> by lazy { MutableLiveData<BlockedUser>(BlockedUser()) }

    private var blockList = mutableListOf<BlockedUser>() // 裝全部 blockedUsers

    fun initialize(){
        viewModelScope.launch {
            member.value = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
            loadBlockedUser()
        }
    }
    private fun loadBlockedUser() {
        val urlBlock = "http://10.0.2.2:8080/THP101G2-WebServer-School/member/block/${member.value!!.memberNo}"
        val type = object : TypeToken<MutableList<BlockedUser>>() {}.type
        val list = requestTask<MutableList<BlockedUser>>(urlBlock, respBodyType = type)
        blockedUsers.value = list
        blockedUsers.value?.let {
            blockList = it
        }
    }

    //解除封鎖他人
    fun unblock(id: Int?) {
        requestTask<JsonObject>(
            "http://10.0.2.2:8080/THP101G2-WebServer-School/member/block/$id",
            "DELETE"
        )
    }

    fun searchBlockedUser(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            blockedUsers.value = blockList
        } else {
            val searchList = mutableListOf<BlockedUser>()
            blockList.forEach { blockedUser ->
                if (blockedUser.userId.contains(newText, true)) {
                    searchList.add(blockedUser)
                }
            }
            blockedUsers.value = searchList
        }
    }

}