package com.example.thp101g2_android_school.community.model

import java.io.Serializable

data class ReplyLikeBean(
    val comReplyLikeId: String? = null,
    val memberNo: String? = null,
    val comReplyId: String? = null,
    val comReplyEmotion: Boolean? = null
) : Serializable