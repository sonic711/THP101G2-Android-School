package com.example.thp101g2_android_school.manage.model

import java.io.Serializable
import java.sql.Timestamp

data class ManageAccountBean (
            var manageId: String? = null,
            var manageAc: String? = null,
            var managePass:String? = null,
            var manageName:String? = null,
            var manageReason:String? = null,
            var manageRtime:String? = null,
            var managePtime:String? = null,
            var manageResult:Boolean? = null,
            var courseName:String? = null,
            var summary:String? = null,
            var video:Byte? = null,
            var addAndRemove:Boolean? = null,

) : Serializable