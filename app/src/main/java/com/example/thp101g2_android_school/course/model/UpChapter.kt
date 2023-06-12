package com.example.thp101g2_android_school.course.model

import java.io.Serializable

data class UpChapter(
    var chapterId: Int? = null,
    var chapterName: String? = "",
    var courseName: String? = "",
    var courseId: Int? = null,
    var video:String? = "",
    var chapterSequence: String? = "",
    var updateTime: String? = null
) : Serializable