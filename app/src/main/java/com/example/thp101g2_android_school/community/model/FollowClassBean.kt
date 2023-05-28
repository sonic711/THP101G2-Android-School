package com.example.thp101g2_android_school.community.model

import java.io.Serializable

data class FollowClassBean(
    var memberFollowBoardId: String,
    var memberNo: String,
    var comSecClassId: String
) : Serializable