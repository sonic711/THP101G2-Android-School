package com.example.thp101g2_android_school.point.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.PostBean
import com.example.thp101g2_android_school.member.model.Member
import com.example.thp101g2_android_school.point.model.Point
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*



class PointViewModel : ViewModel() {
    private var reasonList = mutableListOf<Point>()
    val reasons: MutableLiveData<List<Point>> by lazy { MutableLiveData<List<Point>>() }









    init {
        loadData()
    }

    fun loadData(){
        var currentMember: Member? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
        val MNO = currentMember?.memberNo
        val type = object : TypeToken<List<Point>>() {}.type
        val list = requestTask<List<Point>>("$url/point/$MNO", respBodyType = type) ?: return
        this.reasonList = list.toMutableList()
        this.reasons.value = reasonList

    }

}