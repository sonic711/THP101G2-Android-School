package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.ClassBean
import com.example.thp101g2_android_school.community.model.Post
import com.example.thp101g2_android_school.community.model.PostBean
import com.example.thp101g2_android_school.community.model.Reply
import com.google.gson.reflect.TypeToken

class PostDetailViewModel : ViewModel() {
    val post: MutableLiveData<Post> by lazy { MutableLiveData<Post>() }
    val replys: MutableLiveData<List<Reply>> by lazy { MutableLiveData<List<Reply>>() }

     fun loadData(){
        val url = "$url/community/reply/${post.value?.comPostId}"
        val type = object : TypeToken<List<Reply>>() {}.type
        val replys = requestTask<List<Reply>>(url, respBodyType = type) ?: return
         val replyList = mutableListOf<Reply>()
         for(reply in replys){
             replyList.add(reply)
         }
         this.replys.value = replyList
    }
}