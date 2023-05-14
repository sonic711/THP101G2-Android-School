package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.community.model.Post

class ComMainViewModel : ViewModel() {
    val post: MutableLiveData<Post> by lazy { MutableLiveData<Post>() }
}