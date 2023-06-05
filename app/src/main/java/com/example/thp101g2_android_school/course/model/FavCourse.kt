package com.example.thp101g2_android_school.course.model

import java.io.Serializable

data class FavCourse (
    var courseName: String? = "",
    var userId: String? = "",
    var favoriteCoursesId : String? = "",
    var courseId: String? = "",
    var memberNo: String? = "",
    var image: ByteArray? = null,
    var favoriteCourses: Boolean? = null,
    var updateTime: String? = null
    ) : Serializable