package com.example.thp101g2_android_school.course.model

import java.io.Serializable

data class Comment  (
    var commentId: String? = "",
    var memberNo: String? = "",
    var courseId: String? = "",
    var comment: String? = "",
    var commentReport: Boolean? = null,
    var updateTime: String? = null,
    var image: ByteArray? = null,
    var userId: String? = "",
    var courseName: String? = "",
    var rating: String? =""
    ) : Serializable