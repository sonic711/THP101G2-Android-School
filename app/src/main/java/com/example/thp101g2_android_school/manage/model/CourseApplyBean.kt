package com.example.thp101g2_android_school.manage.model

import android.media.MediaSession2Service.MediaNotification
import java.io.Serializable
import java.sql.Timestamp


//
data class CourseApplyBean(
        var courseApplyId: String? = null,
        var courseId: String? = null,
        var manageId: String? = null,
        var memberNo: String? = null,
        var classApplyTime: String? = null,
        var managePtime: String? = null,
        var classCheck: Boolean? = null,
        var classResult: Boolean? = null,
        ) : Serializable

