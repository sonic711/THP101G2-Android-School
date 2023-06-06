package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.course.model.MyCourse

class CouMyCourseDetailViewModel : ViewModel() {
    val mycourse: MutableLiveData<MyCourse> by  lazy { MutableLiveData<MyCourse>() }
    val test =  if (mycourse?.value?.coursesProgress == true) {
         "已完成"
    }else{
        "未完成"
    }

//    if (mycourse.value.coursesProgress == true) {
//        test.value = "已完成"
//    }else{
//        test.value = "未完成"
//    }
}