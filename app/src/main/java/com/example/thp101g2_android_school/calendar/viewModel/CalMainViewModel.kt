package com.example.thp101g2_android_school.calendar.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.calendar.model.Calendar

class CalMainViewModel : ViewModel() {
    val schedule: MutableLiveData<Calendar> by lazy { MutableLiveData<Calendar>() }

}