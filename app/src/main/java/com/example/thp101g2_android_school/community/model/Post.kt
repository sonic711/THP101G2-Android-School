package com.example.thp101g2_android_school.community.model

import java.io.Serializable

/*
*  需要join 會員跟次分類
* */

data class Post(
    var comPostId: String,
    var memberNo : String,
    var memberName: String,
    var memberImg: Int,
    var comSecClassId: String,
    var comSecClassName: String,
    var comPostTitle: String,
    var comPostContent: String,
    var labels: List<Label>,
    var comPostTime: String,
    var comPostStatus: Boolean,
    var comPostAccessSetting: Boolean
) : Serializable