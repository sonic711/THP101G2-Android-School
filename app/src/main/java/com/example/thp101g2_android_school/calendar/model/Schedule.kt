package com.example.thp101g2_android_school.calendar.model

import java.sql.Date
import java.sql.Time

class Schedule(
    var scheduleId: Int? = null,
    var memeberNo: Int? = null,
    var task: String = "",
    var tagUserDefId: Int? = null, // 就是 tagId
    var date: Date? = null,
    var startTime: Time? = null,
    var endTime: Time? = null,
    var repeatPattern: Int? = null,
    var reminder: Int? = null
): java.io.Serializable {

}