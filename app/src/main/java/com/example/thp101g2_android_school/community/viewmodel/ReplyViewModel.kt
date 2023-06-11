package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.Reply
import com.example.thp101g2_android_school.community.model.ReplyAndLike
import com.example.thp101g2_android_school.community.model.ReplyLikeBean
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class ReplyViewModel : ViewModel() {
    val reply: MutableLiveData<ReplyAndLike> by lazy { MutableLiveData<ReplyAndLike>(ReplyAndLike()) }
    fun addLike(member: Member) {
        val jsonObj = JsonObject()
        jsonObj.addProperty("memberNo", member.memberNo)
        jsonObj.addProperty("comReplyId", reply.value?.comReplyId)
        jsonObj.addProperty("comReplyEmotion", true)
        val respbody = requestTask<JsonObject>("$url/community/replyLike", "POST", jsonObj)
    }

    fun cancelLike(member: Member) {
        val memberNo = member.memberNo
        val replyId = reply.value?.comReplyId
        val respbody = requestTask<JsonObject>("$url/community/replyLike/$replyId/$memberNo", "DELETE")
    }
//    fun getReplyLikes(replyToId: String?) {
//        val memberId = 1
//        val url = "$url/community/replyLike/$replyToId"
//        val type = object : TypeToken<List<ReplyLikeBean>>() {}.type
//        val replyLikes = requestTask<List<ReplyLikeBean>>(url, respBodyType = type) ?: return
//        println(replyLikes)
//        this.replyLikes = replyLikes
//    }
}