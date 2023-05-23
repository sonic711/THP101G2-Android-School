package com.example.thp101g2_android_school.community.model

data class Reply(
    val comReplyId: String,
    val memberNo: String,
    val memberName: String,
    val memberImg: Int,
    val comReplyTo: String,
    val comReplyContent: String,
    val comReplyTime: String,
    val comReplyAccessSetting: String,
)