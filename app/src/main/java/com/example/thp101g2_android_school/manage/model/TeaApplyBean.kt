package com.example.thp101g2_android_school.manage.model

import java.io.Serializable
import java.sql.Timestamp

data class TeaApplyBean (
    var teaId:String? = null,
    var manageId: String? = null,
    var memberNo: String? = null,
    var nickName: String? = null,
    var manageRtime: String? = null,
    var managePtime: String? = null,
    var teaCheck: String? = null,
    var teaResult: Boolean? = null,
) : Serializable