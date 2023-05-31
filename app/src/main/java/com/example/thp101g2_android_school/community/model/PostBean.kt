package com.example.thp101g2_android_school.community.model

import java.io.Serializable
import java.sql.Timestamp

/*
*  需要join 會員跟次分類
* */

data class PostBean(
    var comPostId: String? = null,
    var memberNo : String? = null,
    var userId: String? = null,
    var nickName: String? = null,
    var profilePhoto: ByteArray? = null,
    var comSecClassId: String? = null,
    var comSecClassName: String? = null,
    var comPostTitle: String? = null,
    var comPostContent: String? = null,
    var comPostLabelId: String? = null,
    var comPostLabelName: String? = null,
    var comPostLabelTime: String? = null,
    var comPostTime: String? = null,
    var comPostStatus: Boolean? = null,
    var comPostAccessSetting: Boolean? = null,
) : Serializable