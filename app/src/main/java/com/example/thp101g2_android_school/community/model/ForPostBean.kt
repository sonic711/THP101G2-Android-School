package com.example.thp101g2_android_school.community.model

data class ForPostBean(
    var memberNo : String? = "",
    var comSecClassId: String? = "",
    var comPostTitle: String? = "",
    var comPostContent: String? = "",
    var comPostStatus: Boolean? = false,
    var labels: List<Label>? = null
    )