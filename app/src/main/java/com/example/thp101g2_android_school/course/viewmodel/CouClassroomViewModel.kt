package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.course.model.Chapter
import com.example.thp101g2_android_school.course.model.FavCourse
import com.google.gson.reflect.TypeToken

class CouClassroomViewModel : ViewModel() {
    private var roomcoursesList = mutableListOf<Chapter>()
    val roomcourses: MutableLiveData<List<Chapter>> by lazy { MutableLiveData<List<Chapter>>() }



    init {
        loadData()
    }
    private fun loadData() {
        val url = "$url/chapter/1"
        val type = object : TypeToken<List<Chapter>>() {}.type
        val list = requestTask<List<Chapter>>(url, respBodyType = type)
        for (roomcourses in list!!){
            roomcoursesList.add(roomcourses)
        }
        this.roomcoursesList = roomcoursesList
        this.roomcourses.value = this.roomcoursesList
    }


}