package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.course.model.Courses

class CouTeacherDetailViewModel: ViewModel() {
    val course: MutableLiveData<Courses> by lazy { MutableLiveData<Courses>() }

}