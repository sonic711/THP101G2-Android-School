package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.Label
import com.example.thp101g2_android_school.course.model.Course
import com.example.thp101g2_android_school.course.model.Courses
import com.google.gson.reflect.TypeToken
import com.example.thp101g2_android_school.member.model.Member

class CouMainViewModel : ViewModel() {

        private var courseList = mutableListOf<Courses>()
        val courses: MutableLiveData<List<Courses>> by lazy { MutableLiveData<List<Courses>>() }

    init {

        loadData()


    }


     fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            courses.value = courseList
        } else {
            val searchCourseList = mutableListOf<Courses>()
            courseList.forEach { courses ->
                if (courses.courseName?.contains(newText, true) == true || (courses.userId?.contains(newText, true)) == true) {
                    searchCourseList.add(courses)
                }
            }
           courses.value = searchCourseList
        }
    }
    private fun loadData() {

        val url = "$url/course/"
        val type = object : TypeToken<List<Courses>>() {}.type
        val list = requestTask<List<Courses>>(url, respBodyType = type)
        for (courses in list!!) {
            courseList.add(courses)
        }
        this.courseList = courseList
        this.courses.value =  this.courseList
    }
}