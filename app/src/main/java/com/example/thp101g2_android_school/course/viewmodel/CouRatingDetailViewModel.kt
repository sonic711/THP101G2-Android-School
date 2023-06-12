package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.course.model.Comment
import com.example.thp101g2_android_school.member.model.Member

class CouRatingDetailViewModel : ViewModel(){
    val rating: MutableLiveData<Comment> by  lazy { MutableLiveData<Comment>() }

}