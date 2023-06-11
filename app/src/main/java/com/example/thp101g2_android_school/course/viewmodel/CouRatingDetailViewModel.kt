package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.course.model.Comment
import com.example.thp101g2_android_school.member.model.Member

class CouRatingDetailViewModel : ViewModel(){
    val rating: MutableLiveData<Comment> by  lazy { MutableLiveData<Comment>() }
    init {
        val member: Member? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
        member?.memberNo
    }
}