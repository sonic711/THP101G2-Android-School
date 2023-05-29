package com.example.thp101g2_android_school.point.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.PostBean
import com.example.thp101g2_android_school.point.model.Point
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*



class PointViewModel : ViewModel() {
    private var reasonList = mutableListOf<Point>()
    val reasons: MutableLiveData<List<Point>> by lazy { MutableLiveData<List<Point>>() }

    init {
        loadData()
    }

    private fun loadData(){
        val type = object : TypeToken<List<Point>>() {}.type
        val list = requestTask<List<Point>>("$url/point/2", respBodyType = type) ?: return
        this.reasonList = list.toMutableList()
        this.reasons.value = reasonList

    }

}