package com.example.thp101g2_android_school.member.model

data class Member(
    var memberNo: Int? = null,
    var userId: String = "",
    var password: String = "",
    var nickname: String = "",
    var memberIdentity: String = "",
    var memberEmail: String = "",
    var phoneNumber: String = "",
    var profilePhoto64: String? = null, // Base64
    var coverPicture64: String? = null, // Base64
    var memberStatus: Int? = null,
    var introduction: String = "",
    var rewardPoints: Int? = 0
    ): java.io.Serializable