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
//    fun AddFav () {
//            val test2 = FavCourse(
//
//                courseId = course.value?.courseId!!,
//                memberNo = course?.value?.memberNo!!
//
//
//            )
//            requestTask<JsonObject>(
//                "http://10.0.2.2:8080/THP101G2-WebServer-School/favoritecourses/",
//                method = "POST",
//                reqBody = test2
//            )
//
//        }
//    fun AddStu(){
//        val test = MyCourse(
//            courseId = course.value?.courseId!!,
//            memberNo = course?.value?.memberNo!!
//        )
//        requestTask<JsonObject>(
//            "http://10.0.2.2:8080/THP101G2-WebServer-School/studentcourses/",
//            method = "POST",
//            reqBody = test
//        )
//    }
}
