package com.example.thp101g2_android_school.course.model

import java.io.Serializable

data class FavCourse(
    var courseName: String? = null,
    var userId: String? = null,
    var favoriteCoursesId: Int? =null ,
    var courseId: Int,
    var memberNo:Int,
    var image: ByteArray? = null,
    var favoriteCourses: Boolean? = null,
    var updateTime: String? = null,
) : Serializable