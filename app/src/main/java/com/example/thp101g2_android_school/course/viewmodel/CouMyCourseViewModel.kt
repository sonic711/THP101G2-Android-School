package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.course.model.FavCourse
import com.example.thp101g2_android_school.course.model.MyCourse
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.reflect.TypeToken


class CouMyCourseViewModel : ViewModel() {
    private var mycourseList = mutableListOf<MyCourse>()
    val mycourses: MutableLiveData<List<MyCourse>> by lazy { MutableLiveData<List<MyCourse>>() }

    init {
        val member: Member? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
        member?.memberNo
        loadData()

    }


    private fun loadData() {
        val url = "$url/studentcourses/1"
        val type = object : TypeToken<List<MyCourse>>() {}.type
        val list = requestTask<List<MyCourse>>(url, respBodyType = type)
        for (mycourses in list!!){
            mycourseList.add(mycourses)
        }
        this.mycourseList = mycourseList
        this.mycourses.value = this.mycourseList
    }



}