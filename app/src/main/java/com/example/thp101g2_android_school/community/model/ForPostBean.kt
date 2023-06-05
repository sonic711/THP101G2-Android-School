package com.example.thp101g2_android_school.community.model

data class ForPostBean(
    var memberNo : String? = null,
    var comSecClassId: String? = null,
    var comPostTitle: String? = null,
    var comPostContent: String? = null,
    var comPostStatus: Boolean? = false,
    var labels: List<Label>? = null
    )