package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.course.model.Course

class CouMainDetailViewModel: ViewModel() {
    val course: MutableLiveData<Course> by lazy { MutableLiveData<Course>() }
}