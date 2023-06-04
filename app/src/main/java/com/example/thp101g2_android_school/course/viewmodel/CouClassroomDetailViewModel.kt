package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.course.model.Chapter

class CouClassroomDetailViewModel : ViewModel(){
    val roomcourse: MutableLiveData<Chapter> by lazy { MutableLiveData<Chapter>() }
}