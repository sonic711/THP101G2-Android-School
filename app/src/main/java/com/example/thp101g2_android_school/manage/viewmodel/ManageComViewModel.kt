package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.thp101g2_android_school.manage.model.Comms

class ManageComViewModel : ViewModel() {
    val commo: MutableLiveData<Comms> by lazy { MutableLiveData<Comms>() }
}

