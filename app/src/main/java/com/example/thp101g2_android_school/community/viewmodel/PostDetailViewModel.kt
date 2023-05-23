package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.community.model.Post
import com.example.thp101g2_android_school.community.model.Reply

class PostDetailViewModel : ViewModel() {
    val post: MutableLiveData<Post> by lazy { MutableLiveData<Post>() }
    val reply: MutableLiveData<Reply> by lazy { MutableLiveData<Reply>() }
    val replys: MutableLiveData<List<Reply>> by lazy { MutableLiveData<List<Reply>>() }

}