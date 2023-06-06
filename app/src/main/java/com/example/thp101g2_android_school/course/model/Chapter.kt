package com.example.thp101g2_android_school.course.model

import java.io.Serializable

data class Chapter (
    var chapterId: Int? = null,
    var chapterName: String? = "",
    var courseName: String? = "",
    var courseId: Int,
    var video:String? = "",
    var chapterSequence: Int,
    var updateTime: String? = null
    ) : Serializable