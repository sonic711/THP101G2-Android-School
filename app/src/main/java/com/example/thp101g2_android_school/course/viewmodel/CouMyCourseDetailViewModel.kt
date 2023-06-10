package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.course.model.MyCourse
import com.example.thp101g2_android_school.member.model.Member

class CouMyCourseDetailViewModel : ViewModel() {
    val mycourse: MutableLiveData<MyCourse> by  lazy { MutableLiveData<MyCourse>() }
    val test =  if (mycourse?.value?.coursesProgress == true) {
         "已完成"
    }else{
        "未完成"
    }

    init {
        val member: Member? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
        member?.memberNo
    }

//    if (mycourse.value.coursesProgress == true) {
//        test.value = "已完成"
//    }else{
//        test.value = "未完成"
//    }
}