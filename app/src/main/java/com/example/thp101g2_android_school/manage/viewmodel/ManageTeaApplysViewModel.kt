package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.manage.model.TeaApplyBean
import com.google.gson.reflect.TypeToken

class ManageTeaApplysViewModel : ViewModel() {

    private var teaapplyList = mutableListOf<TeaApplyBean>()
    val teaapplies: MutableLiveData<List<TeaApplyBean>> by lazy { MutableLiveData<List<TeaApplyBean>>() }

    init {
        loadTeaApply()
    }

    fun searchteaapply(newText: String?) {
        if (newText.isNullOrEmpty()) {
            teaapplies.value = teaapplyList
        } else {
            val filteredTeaApply = teaapplyList.filter { teaapply ->
                (teaapply.manageId?.contains(newText, ignoreCase = true)) == true
            }
            teaapplies.value = filteredTeaApply
        }
    }
    private fun loadTeaApply() {
        val url2 = "http://10.0.2.2:8080/THP101G2-WebServer-School/teaapply/ddd"


        val type2 = object : TypeToken<List<TeaApplyBean>>() {}.type

        val list2 = requestTask<List<TeaApplyBean>>(url2, respBodyType = type2)


        teaapplyList.addAll(list2 ?: emptyList())
        this.teaapplyList = teaapplyList
        teaapplies.value = teaapplyList

    }

}