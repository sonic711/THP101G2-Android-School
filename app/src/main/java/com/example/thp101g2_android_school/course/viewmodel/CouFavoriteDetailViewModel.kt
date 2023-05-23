package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.course.model.FavCourse

class CouFavoriteDetailViewModel : ViewModel(){
    val favcourse: MutableLiveData<FavCourse> by lazy { MutableLiveData<FavCourse>() }
}