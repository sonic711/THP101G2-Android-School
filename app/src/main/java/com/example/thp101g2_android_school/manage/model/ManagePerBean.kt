package com.example.thp101g2_android_school.manage.model

import java.io.Serializable


//管理身分
data class ManagePerBean (
    var manageApplyId :String? = null,
    var manageCom: Boolean? = null,
    var manageFirm: Boolean? = null,
    var manageCourse: Boolean? = null,
    var manageMember: Boolean? = null,
    var manageReport: Boolean? = null,

) : Serializable
