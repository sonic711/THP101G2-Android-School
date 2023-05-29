package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.ChildItem
import com.example.thp101g2_android_school.community.model.FollowClassBean
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class ClassChildViewModel : ViewModel() {
    val child: MutableLiveData<ChildItem> by lazy { MutableLiveData<ChildItem>() }

    // TODO 先寫死會員編號1 假定登入
    fun addFollow() {
        val jsonObj = JsonObject()
        jsonObj.addProperty("memberNo", 1)
        jsonObj.addProperty("comSecClassId", child.value?.comSecClassId)
        val respbody = requestTask<JsonObject>("$url/member/followClass", "POST", jsonObj)

    }
    // TODO 先寫死會員編號1 假定登入
    fun cancelFollow(){
        val memberNo = 1
        val classId = child?.value?.comSecClassId
        val respbody = requestTask<Boolean>("$url/member/followClass/$memberNo/$classId", "DELETE")

    }
}