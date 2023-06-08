package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.manage.model.CourseReportBean

/**
 * 單一好友資料處理
 */
class ManageClassViewModel : ViewModel() {
    val classo: MutableLiveData<CourseReportBean> by lazy { MutableLiveData<CourseReportBean>() }
}
