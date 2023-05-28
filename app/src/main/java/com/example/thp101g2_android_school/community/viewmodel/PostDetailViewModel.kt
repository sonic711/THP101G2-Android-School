package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.ClassBean
import com.example.thp101g2_android_school.community.model.Post
import com.example.thp101g2_android_school.community.model.PostBean
import com.example.thp101g2_android_school.community.model.Reply
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class PostDetailViewModel : ViewModel() {
    val post: MutableLiveData<Post> by lazy { MutableLiveData<Post>() }
    val replys: MutableLiveData<List<Reply>> by lazy { MutableLiveData<List<Reply>>() }
    val replyContent: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun loadData() {
        val url = "$url/community/reply/${post.value?.comPostId}"
        val type = object : TypeToken<List<Reply>>() {}.type
        val replys = requestTask<List<Reply>>(url, respBodyType = type) ?: return
        val replyList = mutableListOf<Reply>()
        for (reply in replys) {
            replyList.add(reply)
        }
        this.replys.value = replyList
    }
    fun sendReply(){
        val reply = Reply()
        // TODO 先寫死登入的會員編號是1的
        reply.memberNo = "1"
        reply.comReplyTo = post?.value?.comPostId
        reply.comReplyContent = replyContent?.value?.trim()
        println(reply)
        val respbody = requestTask<JsonObject>("$url/community/reply", "POST", reply)
    }
}