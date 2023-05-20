package com.example.thp101g2_android_school.calendar.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalScheduleItemViewModel : ViewModel() {
    val day: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val month: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val tag: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val task: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val time: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}