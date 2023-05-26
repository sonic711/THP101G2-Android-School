package com.example.thp101g2_android_school.community.model

data class Reply(
    val comReplyId: String? = "",
    val memberNo: String? = "",
    val userId: String? = "",
    val nickName: String? = "",
    val profilePhoto: ByteArray? = null,
    val comReplyTo: String? = "",
    val comReplyContent: String? = "",
    val comReplyTime: String? = "",
    val comReplyAccessSetting: Boolean? = null
)