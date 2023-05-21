package com.example.thp101g2_android_school.calendar.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.calendar.model.Schedule

class CalScheduleItemViewModel : ViewModel() {
    val schedule: MutableLiveData<Schedule> by lazy { MutableLiveData<Schedule>() }
}