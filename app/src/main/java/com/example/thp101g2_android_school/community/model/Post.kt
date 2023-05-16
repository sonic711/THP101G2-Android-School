package com.example.thp101g2_android_school.community.model

import java.io.Serializable

class Post(var postId: String,var memberName: String,var memberImg: Int, var secClass: String, var time: String, var postTitle: String, var postContent: String, var labels: List<Label>):Serializable {
}