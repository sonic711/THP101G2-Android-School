package com.example.thp101g2_android_school.calendar.model

class Calendar(
    var task: String,
    var tagColor: String,
    var tagName: String,
    var date: String,
    var startTime: String,
    var endTime: String,
    var repeat: String? = null,
    var remind: String? = null
    ): java.io.Serializable {

}