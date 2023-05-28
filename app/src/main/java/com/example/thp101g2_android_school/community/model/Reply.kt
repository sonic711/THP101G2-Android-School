package com.example.thp101g2_android_school.community.model

data class Reply(
    var comReplyId: String? = null,
    var memberNo: String? = null,
    var userId: String? = null,
    var nickName: String? = null,
    var profilePhoto: ByteArray? = null,
    var comReplyTo: String? = null,
    var comReplyContent: String? = null,
    var comReplyTime: String? = null,
    var comReplyAccessSetting: Boolean? = null
)