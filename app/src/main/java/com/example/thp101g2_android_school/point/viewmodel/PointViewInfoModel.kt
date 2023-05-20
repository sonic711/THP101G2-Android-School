package com.example.thp101g2_android_school.point.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.point.model.Point

class PointViewInfoModel: ViewModel() {
    val reason: MutableLiveData<Point> by lazy { MutableLiveData<Point>() }
}