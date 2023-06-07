package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.course.model.Courses
import com.example.thp101g2_android_school.course.model.FavCourse
import com.example.thp101g2_android_school.course.model.MyCourse
import com.google.gson.JsonObject

class CouMainDetailViewModel: ViewModel() {
    val course: MutableLiveData<Courses> by lazy { MutableLiveData<Courses>() }
}
