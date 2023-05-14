package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.thp101g2_android_school.community.model.ParentItem

class ClassParentViewModel {
    val parent: MutableLiveData<ParentItem> by lazy { MutableLiveData<ParentItem>() }
}