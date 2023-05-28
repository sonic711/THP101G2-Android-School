package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.community.model.Reply

class ReplyViewModel: ViewModel() {
    val reply: MutableLiveData<Reply> by lazy { MutableLiveData<Reply>() }
}