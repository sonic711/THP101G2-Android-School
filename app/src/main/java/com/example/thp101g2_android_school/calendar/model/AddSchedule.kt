package com.example.thp101g2_android_school.calendar.model

import java.sql.Date
import java.sql.Time

class AddSchedule(
    var task: String,
    var tagName: String,
    var date: Date,
    var startTime: Time,
    var endTime: Time,
    var repeat: String? = null,
    var remind: String? = null
): java.io.Serializable {

}