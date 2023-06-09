package com.example.thp101g2_android_school.manage.model

import okio.ByteString
import java.io.Serializable
import java.sql.Timestamp

//class
data class CourseReportBean(
    var courseReportId: String? = null,
    var manageId: String? = null,
    var courseId: String? = null,
    var memberNo: String? = null,
    var manageReason: String? = null,
    var manageRtime: String? = null,
    var managePtime: String? = null,
    var manageResult: Boolean? = null,
    var courseName: String? = null,
    val summary:String? = null,
    var video: ByteArray? = null,
    var addAndRemove:Boolean? = null




    ) : Serializable



