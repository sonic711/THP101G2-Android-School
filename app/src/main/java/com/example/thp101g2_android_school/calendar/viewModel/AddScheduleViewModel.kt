package com.example.thp101g2_android_school.calendar.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.calendar.model.AddSchedule
import com.example.thp101g2_android_school.calendar.model.MemSchedule
import com.example.thp101g2_android_school.calendar.model.Schedule
import com.example.thp101g2_android_school.calendar.model.Tag
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class AddScheduleViewModel : ViewModel() {
    private val myTag = "TAG_${javaClass.simpleName}"
    val schedule: MutableLiveData<Schedule> by lazy { MutableLiveData<Schedule>(Schedule()) }

    val tagName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val date: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val startTime: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val endTime: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    var member: Member? = Member()

    val tags: MutableLiveData<MutableList<Tag>> by lazy { MutableLiveData<MutableList<Tag>>() }
    val tag: MutableLiveData<Tag> by lazy { MutableLiveData<Tag>(Tag()) }

    fun initialize() {
        viewModelScope.launch {
            member = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
            loadTags()
        }
    }

    private fun loadTags() {
        val urlTag = "http://10.0.2.2:8080/THP101G2-WebServer-School/calendar/userTag/${member?.memberNo}/2023/05/01"
        val type = object : TypeToken<MutableList<Tag>>() {}.type
        val list = requestTask<MutableList<Tag>>(urlTag, respBodyType = type)
        tags.value = list
    }

    fun addSchedule() {
        val urlAddSchedule = "http://10.0.2.2:8080/THP101G2-WebServer-School/calendar/schedule"
        val respBody = requestTask<JsonObject>(urlAddSchedule, "POST", schedule.value)
        Log.d(myTag, respBody.toString())
    }

    fun editTag(tag: Tag) {
        val urlEditTag = "http://10.0.2.2:8080/THP101G2-WebServer-School/calendar/userTag"
        val respBody = requestTask<JsonObject>(urlEditTag, "PUT", tag)
        Log.d(myTag, respBody.toString())
    }

}