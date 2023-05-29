package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.Post
import com.example.thp101g2_android_school.community.model.Reply
import com.example.thp101g2_android_school.community.model.ReplyLikeBean
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class PostDetailViewModel : ViewModel() {
    val post: MutableLiveData<Post> by lazy { MutableLiveData<Post>() }
    val replys: MutableLiveData<List<Reply>> by lazy { MutableLiveData<List<Reply>>() }
    val replyContent: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val replyList = mutableListOf<Reply>()
    var replyLikes = listOf<ReplyLikeBean>()

    init {
        viewModelScope.launch {
            getLikes()
            loadData()
        }
    }
    // TODO 修正loadData()無法觸發的問題
    fun loadData() {
        val url = "$url/community/reply/${post.value?.comPostId}"
        val type = object : TypeToken<List<Reply>>() {}.type
        val replys = requestTask<List<Reply>>(url, respBodyType = type) ?: return

        this.replys.value = replys.toList()


    }

    fun sendReply() {
        val reply = Reply()
        // TODO 先寫死登入的會員編號是1的 然後剛回文的當下無法顯示圖片 FIXIT
        reply.memberNo = "1"
        reply.nickName = "Adam"
        reply.userId = "adam0823"
        reply.comReplyTo = post?.value?.comPostId
        reply.comReplyContent = replyContent?.value?.trim()
        replyList.add(reply)
        replys.value = replyList

        val respbody = requestTask<JsonObject>("$url/community/reply", "POST", reply)
    }

    fun getLikes() {
        val memberId = 1
        val url = "$url/community/replyLike/$memberId"
        val type = object : TypeToken<List<ReplyLikeBean>>() {}.type
        val replyLikes = requestTask<List<ReplyLikeBean>>(url, respBodyType = type) ?: return
        this.replyLikes = replyLikes
    }
}