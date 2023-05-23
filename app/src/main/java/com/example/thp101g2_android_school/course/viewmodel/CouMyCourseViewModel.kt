package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.course.model.MyCourse


class CouMyCourseViewModel : ViewModel() {
    private var mycourseList = mutableListOf<MyCourse>()
    val mycourses: MutableLiveData<List<MyCourse>> by lazy { MutableLiveData<List<MyCourse>>() }

    init {
        loadMycourses()
    }

    private fun loadMycourses(){
        val mycourseList = mutableListOf<MyCourse>()
        mycourseList.add(MyCourse(R.drawable.java, "Java","Ron","5分(600個評論)"))
        mycourseList.add(MyCourse(R.drawable.kotlin, "Kotlin","Ron","5分(450個評論)"))
        mycourseList.add(MyCourse(R.drawable.python, "Python","William","4分(300個評論)"))
        mycourseList.add(MyCourse(R.drawable.python, "Python","William","4分(300個評論)"))
        mycourseList.add(MyCourse(R.drawable.python, "Python","William","4分(300個評論)"))
        mycourseList.add(MyCourse(R.drawable.python, "Python","William","4分(300個評論)"))
        mycourseList.add(MyCourse(R.drawable.python, "Python","William","4分(300個評論)"))
        mycourseList.add(MyCourse(R.drawable.python, "Python","William","4分(300個評論)"))
        mycourseList.add(MyCourse(R.drawable.python, "Python","William","4分(300個評論)"))
        mycourseList.add(MyCourse(R.drawable.python, "Python","William","4分(300個評論)"))
        mycourseList.add(MyCourse(R.drawable.python, "Python","William","4分(300個評論)"))
        mycourseList.add(MyCourse(R.drawable.python, "Python","William","4分(300個評論)"))
        mycourseList.add(MyCourse(R.drawable.python, "Python","William","4分(300個評論)"))
        this.mycourseList = mycourseList
        this.mycourses.value = this.mycourseList
    }

}