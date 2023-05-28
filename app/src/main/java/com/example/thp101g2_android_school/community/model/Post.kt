package com.example.thp101g2_android_school.community.model

import java.io.Serializable

/*
*  需要join 會員跟次分類
* */

data class Post(
    var comPostId: String? = "",
    var memberNo : String? = "",
    var userId: String? = "",
    var nickName: String? = "",
    var profilePhoto: ByteArray? = null,
    var comSecClassId: String? = "",
    var comSecClassName: String? = "",
    var comPostTitle: String? = "",
    var comPostContent: String? = "",
    var comPostLabelId: String? = "",
    var comPostLabelName: String? = "",
    var comPostLabelTime: String? = "",
    var comPostTime: String? = "",
    var comPostStatus: Boolean? = null,
    var comPostAccessSetting: Boolean? = null,
    var labels: List<Label>? = null,

) : Serializable