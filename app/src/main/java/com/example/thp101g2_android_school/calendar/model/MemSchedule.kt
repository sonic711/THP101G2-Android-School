package com.example.thp101g2_android_school.calendar.model

import java.sql.Date
import java.sql.Time
import java.sql.Timestamp

data class MemSchedule(
    var scheduleId: Int? = null,
    var memberNo: Int? = null,
    var task: String = "",
    var tagUserDefId: Int? = null,
    var tagId: Int? = null,
    var colorHex: String = "#FF66C2", // 字串不能為空，否則 bindingAdapter 會出現錯誤
    var tagName: String = "",
    var date: Date? = null,
    var startTime: Time? = null,
    var endTime: Time? = null,
    var repeatId: Int? = null,
    var repeatPattern: String? = null,
    var remindId: Int? = null,
    var remindType: String? = null

) : java.io.Serializable