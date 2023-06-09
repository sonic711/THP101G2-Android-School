package com.example.thp101g2_android_school.member.model

data class Follower(
    var memberFollowingId: Int? = null,
    var memberNo: Int? = null,
    var memberFollowing: Int? = null,
    var userId: String = "",
    var nickname: String = "",
    var memberIdentity: String = "",
    var profilePhoto64: String? = null, // Base64
    var coverPicture64: String? = null, // Base64
    var memberStatus: Int? = null,
    var introduction: String = ""
): java.io.Serializable{

    override fun toString(): String {
        return "memberNo = $memberNo, userId = $userId, photo = $profilePhoto64 "
    }

}