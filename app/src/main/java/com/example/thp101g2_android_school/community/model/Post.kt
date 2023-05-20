package com.example.thp101g2_android_school.community.model

import java.io.Serializable

data class Post(
    var postId: String,
    var memberId : String,
    var memberName: String,
    var memberImg: Int,
    var secClass: String,
    var time: String,
    var postTitle: String,
    var postContent: String,
    var labels: List<Label>
) : Serializable