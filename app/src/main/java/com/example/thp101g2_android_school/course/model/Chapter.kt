package com.example.thp101g2_android_school.course.model

import java.io.Serializable

data class Chapter (
    var chapterId: String? = "",
    var chapterName: String? = "",
    var courseName: String? = "",
    var courseId: String? = "",
    var video: ByteArray? = null,
    var chapterSequence: String? = "",
    var updateTime: String? = null
    ) : Serializable