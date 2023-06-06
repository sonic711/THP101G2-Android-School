package com.example.thp101g2_android_school.calendar.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.calendar.model.AddSchedule

class AddScheduleViewModel : ViewModel() {
    val addSchedule: MutableLiveData<AddSchedule> by lazy { MutableLiveData<AddSchedule>() }

}