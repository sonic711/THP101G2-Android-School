package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ComPostViewModel : ViewModel() {
    val memberName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val memberImg: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val postTime: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val secClass: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val private: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val title: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val content: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    init {
        loadData()
    }
    private fun loadData(){

    }
}