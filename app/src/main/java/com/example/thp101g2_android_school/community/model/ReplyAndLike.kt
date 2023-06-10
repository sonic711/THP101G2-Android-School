package com.example.thp101g2_android_school.community.model

import java.io.Serializable

data class ReplyAndLike(
    var comReplyId: String? = null,
    var memberNo: String? = null,
    var postMemberNo: String? = null,
    var userId: String? = null,
    var nickName: String? = null,
    var profilePhoto: ByteArray? = null,
    var profilePhoto64: String? = null, // Base64
    var comReplyTo: String? = null,
    var comReplyContent: String? = null,
    var comReplyTime: String? = null,
    var comReplyAccessSetting: String? = null,
    var comReplyLikeId: String? = null,
    var likedMemberNo: String? = null,
    var likedReplyId: String? = null,
    var comReplyEmotion: String? = null,
    var likeCounts: Int? = 0
) : Serializable