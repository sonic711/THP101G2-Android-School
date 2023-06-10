package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.course.model.Courses
import com.example.thp101g2_android_school.course.model.UpCourses

class CouUploadCourseViewModel : ViewModel() {
    val course: MutableLiveData<UpCourses> by lazy { MutableLiveData<UpCourses>() }
    val name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val summary: MutableLiveData<String> by lazy { MutableLiveData<String>() }

}