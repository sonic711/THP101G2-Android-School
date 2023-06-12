package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.course.model.Courses
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.reflect.TypeToken

class CouTeacherViewModel : ViewModel() {
    private var coursesList = mutableListOf<Courses>()
    val courses: MutableLiveData<List<Courses>> by lazy { MutableLiveData<List<Courses>>() }

    init {
        val member: Member? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
        var memberNo = member?.memberNo
        loadData()
    }

    private fun loadData() {
        val url = "$url/course/"
        val type = object : TypeToken<List<Courses>>() {}.type
        val list = requestTask<List<Courses>>(url, respBodyType = type)
        for (course in list!!) {
            coursesList.add(course)
        }
        this.coursesList = coursesList
        this.courses.value = this.coursesList
    }
}