package com.example.thp101g2_android_school.member.model

class Member(
    var userId: String,
    var password: String,
    var nickname: String,
    var memberIdentity: String,
    var memberEmail: String,
    var phoneNumber: String,
    var profilePhoto: ByteArray,
    var coverPhoto: ByteArray,
    var memberStatus: Int,
    var introduction: String,
    var rewardPoints: Int
    ):java.io.Serializable {
}