package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.course.model.FavCourse
import com.google.gson.reflect.TypeToken


class CouFavoriteViewModel : ViewModel() {
    private var favcourseList = mutableListOf<FavCourse>()
    val favcourses: MutableLiveData<List<FavCourse>> by lazy { MutableLiveData<List<FavCourse>>() }


    init {
        loadData()
    }

    fun loadData() {
        val url = "$url/favoritecourses/1"
        val type = object : TypeToken<List<FavCourse>>() {}.type
        val list = requestTask<List<FavCourse>>(url, respBodyType = type)
        for (favcourses in list!!) {
            favcourseList.add(favcourses)
        }
        this.favcourseList = favcourseList
        this.favcourses.value = this.favcourseList
    }


}