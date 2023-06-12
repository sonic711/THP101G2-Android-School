package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.course.model.MyCourse
import com.example.thp101g2_android_school.member.model.Member

class CouMyCourseSelectViewModel : ViewModel() {
    val mycourse: MutableLiveData<MyCourse> by lazy { MutableLiveData <MyCourse>()}

}