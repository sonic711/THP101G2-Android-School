package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.thp101g2_android_school.community.model.Label

class LabelViewModel {
    val label: MutableLiveData<Label> by lazy { MutableLiveData<Label>() }
}