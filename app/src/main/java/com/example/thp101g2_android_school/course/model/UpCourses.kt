package com.example.thp101g2_android_school.course.model

import java.io.Serializable

data class UpCourses (
    var courseId: Int? = null,
    var courseName: String? = "",
    var memberNo: Int? = null,
    var summary: String? = "",
    var addAndRemove: Boolean? = false,
    var coursesReport: Boolean? = false,
    var updateTime: String? = null,
    var image: ByteArray? = null,
    var userId: String? = "",
    var rating: String? = null
) : Serializable