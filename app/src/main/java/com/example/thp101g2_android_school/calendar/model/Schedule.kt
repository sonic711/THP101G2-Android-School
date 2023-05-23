package com.example.thp101g2_android_school.calendar.model

class Schedule(
    var task: String,
    var tagName: String,
    var day: String,
    var month: String,
    var year: String = "2023",
    var time: String,
    var repeat: String? = null,
    var remind: String? = null
): java.io.Serializable {

}