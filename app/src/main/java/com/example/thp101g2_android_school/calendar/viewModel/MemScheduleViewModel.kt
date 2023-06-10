package com.example.thp101g2_android_school.calendar.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.calendar.model.MemSchedule
import com.example.thp101g2_android_school.calendar.model.Schedule
import com.example.thp101g2_android_school.member.model.Follower
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MemScheduleViewModel: ViewModel() {
    private val myTag = "TAG_${javaClass.simpleName}"
    var member: Member? = Member()

    val schedules: MutableLiveData<MutableList<MemSchedule>> by lazy { MutableLiveData<MutableList<MemSchedule>>() }
    val schedule: MutableLiveData<MemSchedule> by lazy { MutableLiveData<MemSchedule>(MemSchedule()) }

    // 日曆首頁的日期
    val date: MutableLiveData<String> by lazy { MutableLiveData<String>(getCurrentDate()) }

    val month: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val day: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val time: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    private var scheduleList = mutableListOf<MemSchedule>() // 裝全部 MemSchedule

    // FIX 好像沒用到
//    val tagColor: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun initialize() {
        viewModelScope.launch {
            member = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
            date.value?.let {
                loadSchedules(it)
            }
        }
    }

    fun loadSchedules(date: String?) {
        Log.d(myTag, "member: ${member?.memberNo}")
        Log.d(myTag, "date: $date")
        val urlSchedule = "http://10.0.2.2:8080/THP101G2-WebServer-School/calendar/schedule/${member?.memberNo}/$date"
        val type = object : TypeToken<MutableList<MemSchedule>>() {}.type
        val list = requestTask<MutableList<MemSchedule>>(urlSchedule, respBodyType = type)
        schedules.value = list
    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun searchDate(date: String?) {
        if (date == null || date.isEmpty()) {
            schedules.value = scheduleList
        } else {
            val searchList = mutableListOf<MemSchedule>()
            scheduleList.forEach { schedule ->
                if (schedule.date.toString().contains(date, true)) {
                    searchList.add(schedule)
                }
            }
            schedules.value = searchList
        }
    }


}