package com.example.thp101g2_android_school.community.model

import java.io.Serializable

data class Notification(
    var notificationId: String? = null,
    var memberNo: String? = null,
    val notificationContent: String? = null,
    val notificationStatus: Boolean? = null,
    val notificationTime: String? = null
) : Serializable