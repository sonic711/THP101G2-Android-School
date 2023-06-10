package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.Post
import com.example.thp101g2_android_school.community.model.ReplyAndLike
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class PostDetailViewModel : ViewModel() {
    val post: MutableLiveData<Post> by lazy { MutableLiveData<Post>() }
    val replyContent: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    // 所有回覆跟喜歡
    val replys: MutableLiveData<List<ReplyAndLike>> by lazy { MutableLiveData<List<ReplyAndLike>>() }

    // 只有回覆
    var replyList = mutableListOf<ReplyAndLike>()
    var allLikes = mutableListOf<ReplyAndLike>()
    var likeCount = mutableMapOf<String, Int>()
    val replyCount: MutableLiveData<Int> by lazy { MutableLiveData<Int>(0) }

    fun loadReplys() {
        val url = "$url/community/reply/${post.value?.comPostId}"
        val type = object : TypeToken<List<ReplyAndLike>>() {}.type
        val replys = requestTask<List<ReplyAndLike>>(url, respBodyType = type) ?: return
        val newReplys = mutableListOf<ReplyAndLike>()
        val idSet = mutableSetOf<String?>()
        var count = 0
        // 取出所有留言跟喜歡紀錄，判斷如果該留言重複，就不放到新的newReplys中
        for (reply in replys.toList()) {
            // 如果已經有這篇回覆就跳過
            if (idSet.contains(reply.comReplyId)) {
                count++
                likeCount["${reply.likedReplyId}"] = count
                continue
            } else {
                // 如果是新的回覆
                count = 1
                likeCount["${reply.likedReplyId}"] = count
                newReplys.add(reply)
                idSet.add(reply.comReplyId)
            }
        }
        for ((key, value) in likeCount) {
            for (reply in newReplys) {
                if (reply.comReplyId == key) {
                    reply.likeCounts = value
                }
            }
        }

        this.replys.value = newReplys
        this.replyCount.value = newReplys.size
        replyList = newReplys
    }

    fun sendReply(member: Member) {
        val reply = ReplyAndLike()
        reply.memberNo = member.memberNo.toString()
        reply.postMemberNo = post.value?.memberNo
        reply.nickName = member.nickname
        reply.userId = member.userId
        reply.profilePhoto64 = member.profilePhoto64
        reply.comReplyTo = post.value?.comPostId
        reply.comReplyContent = replyContent.value?.trim()
        replyList.add(reply)
        replys.value = replyList

        // TODO 這邊應該要回傳通知的Id 然後再送去給通知的Api
        val respbody = requestTask<JsonObject>("$url/community/reply", "POST", reply)
        // 向後端發送通知請求
        val jsonObject = JsonObject()
        jsonObject.addProperty("title", "您的文章已被回覆")
        jsonObject.addProperty("body", "${post.value?.comPostTitle}已被${reply.nickName}回覆")
        jsonObject.addProperty("data", "${respbody?.get("msg")?.asInt}")
        jsonObject.addProperty("memberId", "${post.value?.memberNo}")
        requestTask<JsonObject>("$url/fcm", "POST", jsonObject)
    }
}