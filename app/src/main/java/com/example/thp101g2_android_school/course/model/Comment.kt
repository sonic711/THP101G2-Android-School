package com.example.thp101g2_android_school.course.model

import java.io.Serializable

data class Comment  (
    var commentId:Int? = null,
    var memberNo: Int? = 1,
    var courseId: Int? = 1,
    var comment: String?,
    var commentReport: Boolean? = null,
    var updateTime: String? = null,
    var image: ByteArray? = null,
    var userId: String? = null,
    var courseName: String? = "a",
    var rating: String?
    ) : Serializable
//記得把NULL改掉