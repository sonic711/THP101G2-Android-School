package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.Notification

class ComNotificationViewModel : ViewModel() {
    val notification: MutableLiveData<Notification> by lazy { MutableLiveData<Notification>() }

    fun getNotifi(notifiId: Int) {
        val notifi = requestTask<Notification>("$url/member/notification/$notifiId")
        notification.value = notifi
    }
}