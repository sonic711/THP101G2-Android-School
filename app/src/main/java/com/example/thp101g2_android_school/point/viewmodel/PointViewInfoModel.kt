package com.example.thp101g2_android_school.point.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.getStringResourceId

import com.example.thp101g2_android_school.point.model.Point

class PointViewInfoModel : ViewModel() {
    val reason: MutableLiveData<Point> by lazy { MutableLiveData<Point>() }

}
