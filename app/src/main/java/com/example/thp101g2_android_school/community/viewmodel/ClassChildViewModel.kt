package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.community.model.ChildItem

class ClassChildViewModel: ViewModel() {
    val child: MutableLiveData<ChildItem> by lazy { MutableLiveData<ChildItem>() }
}