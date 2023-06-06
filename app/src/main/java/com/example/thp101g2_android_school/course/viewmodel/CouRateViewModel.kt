package com.example.thp101g2_android_school.course.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.course.model.Comment

class CouRateViewModel : ViewModel() {
   val rates: MutableLiveData<List<Comment>> by lazy { MutableLiveData<List<Comment>>() }
//   val rating = ObservableField<Float>(0f)
//    val comment = ObservableField<String>("")
//
//    fun submitRating() {
//        val userRating = rating.get()
//        val userComment = comment.get()
//    }
}