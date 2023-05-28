package com.example.thp101g2_android_school.course.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class CouRateViewModel : ViewModel() {
    val rating = ObservableField<Float>(0f)
    val comment = ObservableField<String>("")

    fun submitRating() {
        val userRating = rating.get()
        val userComment = comment.get()
    }
}