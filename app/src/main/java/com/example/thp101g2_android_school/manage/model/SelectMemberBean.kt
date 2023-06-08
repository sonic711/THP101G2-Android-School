package com.example.thp101g2_android_school.manage.model

import java.io.Serializable
import java.sql.Timestamp

data class SelectMemberBean (

    var memberNo: String? = null,
    var nickName: String? = null,
    var phoneNumber: String? = null,
    var memberEmail:String? = null,
//    var notificationContent:String? = null,
    var memberStatus:Boolean? = null
) : Serializable