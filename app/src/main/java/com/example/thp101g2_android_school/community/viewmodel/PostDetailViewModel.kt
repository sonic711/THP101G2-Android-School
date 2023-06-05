package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.Post
import com.example.thp101g2_android_school.community.model.ReplyAndLike
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


    // TODO 修正loadReplys無法觸發的問題

    fun loadReplys() {
        val url = "$url/community/reply/${post.value?.comPostId}"
        val type = object : TypeToken<List<ReplyAndLike>>() {}.type
        val replys = requestTask<List<ReplyAndLike>>(url, respBodyType = type) ?: return
        val newReplys = mutableListOf<ReplyAndLike>()
        val idSet = mutableSetOf<String?>()
        // 取出所有留言跟喜歡紀錄，判斷如果該留言重複，就不放到新的newReplys中
        for (reply in replys.toList()) {
            if (idSet.contains(reply.comReplyId)) {
                continue
            } else {
                newReplys.add(reply)
                idSet.add(reply.comReplyId)
            }
        }
        this.allLikes = replys.toMutableList()
        this.replys.value = newReplys
        replyList = newReplys
    }

    fun sendReply() {
        val reply = ReplyAndLike()
        // TODO 先寫死登入的會員編號是1的 然後剛回文的當下無法顯示圖片 FIXIT
        reply.memberNo = "1"
        reply.nickName = "Adam"
        reply.userId = "adam0823"
        reply.comReplyTo = post.value?.comPostId
        reply.comReplyContent = replyContent.value?.trim()
        replyList.add(reply)
        replys.value = replyList

        val respbody = requestTask<JsonObject>("$url/community/reply", "POST", reply)
    }
}