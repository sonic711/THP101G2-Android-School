package com.example.thp101g2_android_school.calendar.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.calendar.model.Calendar
import com.example.thp101g2_android_school.calendar.model.Schedule

class SchedulesViewModel : ViewModel() {
    // 原始日程列表
    private var scheduleList = mutableListOf<Schedule>()
    // 受監控的LiveData，一旦指派新值就會更新好友列表畫面
    val schedules: MutableLiveData<MutableList<Schedule>> by lazy { MutableLiveData<MutableList<Schedule>>() }
    val schedule: MutableLiveData<Calendar> by lazy { MutableLiveData<Calendar>() }


    init {
        loadSchedules()
    }

    private fun loadSchedules() {
        val scheduleList = mutableListOf<Schedule>()
        scheduleList.add(Schedule("做作業", "課業", "20", "MAY", "2023","08:00-10:00"))
        scheduleList.add(Schedule("工作", "工作", "20", "MAY", "2023","11:00-12:00"))
        scheduleList.add(Schedule("閱讀", "休閒", "20", "MAY", "2023","13:00-14:30"))
        scheduleList.add(Schedule("學英文", "進修", "20", "MAY", "2023","15:00-15:30"))
        scheduleList.add(Schedule("學烹飪", "進修", "20", "MAY", "2023","16:00-17:00"))
        scheduleList.add(Schedule("聚餐", "旅遊", "20", "MAY", "2023","18:00-19:30"))
        scheduleList.add(Schedule("閱讀", "休閒", "20", "MAY", "2023","20:00-21:00"))
        this.scheduleList = scheduleList
        this.schedules.value = this.scheduleList

    }
}