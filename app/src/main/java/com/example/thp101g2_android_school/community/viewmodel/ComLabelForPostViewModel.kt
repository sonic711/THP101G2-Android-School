package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.community.model.Label
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class ComLabelForPostViewModel : ViewModel() {
    // 資料庫抓出來的
    private var labelList = mutableListOf<Label>()

    // 受監控的LiveData，一旦指派新值就會更新畫面
    val labels: MutableLiveData<List<Label>> by lazy { MutableLiveData<List<Label>>() }

    init {
        loadData()
    }

    private fun loadData() {

        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/community/postLabel"
        val type = object : TypeToken<List<Label>>() {}.type
        val list = requestTask<List<Label>>(url, respBodyType = type)
        val labelList = mutableListOf<Label>()
        println(list)
        for(label in list!!){
            labelList.add(label)
        }

        this.labelList = labelList
        this.labels.value = this.labelList
    }
}