package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.community.model.ParentItem

class ClassParentViewModel: ViewModel() {
    val parent: MutableLiveData<ParentItem> by lazy { MutableLiveData<ParentItem>() }
}