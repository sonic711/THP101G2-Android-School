package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.course.model.Course

class CouMainViewModel : ViewModel() {
    private var courseList = mutableListOf<Course>()
    val courses: MutableLiveData<List<Course>> by lazy { MutableLiveData<List<Course>>() }

    init {
        loadCourses()
    }


    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            courses.value = courseList
        } else {
            val searchCourseList = mutableListOf<Course>()
            courseList.forEach { course ->
                if (course.name.contains(newText, true)) {
                    searchCourseList.add(course)
                }
            }
           courses.value = searchCourseList
        }
    }


    private fun loadCourses() {
        val courseList = mutableListOf<Course>()
        courseList.add(Course(R.drawable.java, "Java","Ron", "5分(600個評論)"))
        courseList.add(Course(R.drawable.kotlin, "Kotlin","Ron", "5分(450個評論)"))
        courseList.add(Course(R.drawable.python, "Python","William", "4分(300個評論)"))
        this.courseList = courseList
        this.courses.value = this.courseList
    }
}