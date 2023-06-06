package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.course.model.Comment

class CouCommentDetailViewModel: ViewModel() {
    val comment: MutableLiveData<Comment> by lazy { MutableLiveData<Comment>() }
}