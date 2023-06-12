package com.example.thp101g2_android_school.manage.model

import java.io.Serializable
import java.sql.Timestamp

data class ManageComReportBean (
    var manageComReport: Int? = null,
    var manageId: String? = null,
    var comPostId: String? = null,
    var memberNo: String? = null,
    var manageReason: String? = null,
    var manageRtime: String? = null,
    var managePtime: String? = null,
    var manageResult: Boolean? = null,
    var comReplyId: String? = null,
    var comReplyTo: String? = null,
    var comReplyContent :String?  = null,
    var comReplyTime: String? = null,
    var comReplyAccessSetting: Boolean? = null,
    var comPostTitle :String?  = null,
    var comPostContent :String?  = null,
    var comPostTime: String?  = null,
    var comPostStatus: Boolean? = null,
    var comPostAccessSetting : Boolean? = null,

) : Serializable

