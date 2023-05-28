package com.example.thp101g2_android_school.course.model

import java.io.Serializable
import java.sql.Timestamp

data class Courses (
    var courseId: String? = "",
    var courseName: String? = "",
    var memberNo: String? = "",
    var summary: String? = "",
    var addAndRemove: Boolean? = null,
    var coursesReport: Boolean? = null,
    var updateTime: String? = null,
    var image: ByteArray? = null,
    var usrId: String? = "",
    var rating: String? =""
    ) : Serializable