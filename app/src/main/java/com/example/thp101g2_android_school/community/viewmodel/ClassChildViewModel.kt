package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thp101g2_android_school.community.model.ChildItem
import kotlinx.coroutines.launch

class ClassChildViewModel : ViewModel() {
    val child: MutableLiveData<ChildItem> by lazy { MutableLiveData<ChildItem>() }
}