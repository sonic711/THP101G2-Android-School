package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CouUploadChapterViewModel : ViewModel() {
    val chapterName : MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>> (listOf("第一章","第二章","第三章","第四章","第五章"))}
    val text: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    fun uploadVideo(){

    }
}