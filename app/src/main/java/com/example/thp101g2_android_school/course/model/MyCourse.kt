package com.example.thp101g2_android_school.course.model

import java.io.Serializable

data class MyCourse (
    var courseName: String? = "",
    var userId: String? = "",
    var studentCoursesId : String? = "",
    var courseId: String? = "",
    var memberNo: String? = "",
    var coursesProgress: String? = "",
    var image: ByteArray? = null,
    var updateTime: String? = null
    ) : Serializable