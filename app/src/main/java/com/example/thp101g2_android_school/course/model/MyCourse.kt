package com.example.thp101g2_android_school.course.model

import java.io.Serializable

data class MyCourse (
    var courseName: String? = "",
    var userId: String? = "",
    var studentCoursesId : Int? = null,
    var courseId: Int? = 1,
    var memberNo: Int? = 1,
    var coursesProgress: Boolean? = null,
    var image: ByteArray? = null,
    var updateTime: String? = null
    ) : Serializable