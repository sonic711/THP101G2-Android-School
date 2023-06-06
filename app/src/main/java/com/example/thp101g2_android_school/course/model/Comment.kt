package com.example.thp101g2_android_school.course.model

import java.io.Serializable

data class Comment  (
    var commentId:Int? = null,
    var memberNo: Int,
    var courseId: Int,
    var comment: String? = "",
    var commentReport: Boolean? = null,
    var updateTime: String? = null,
    var image: ByteArray? = null,
    var userId: String? = "",
    var courseName: String? = "",
    var rating: Int
    ) : Serializable