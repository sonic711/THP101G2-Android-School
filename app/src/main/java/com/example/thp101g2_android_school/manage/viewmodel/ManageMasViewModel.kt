package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.manage.model.CourseReportBean
import com.example.thp101g2_android_school.manage.model.ManageAccountBean

import com.example.thp101g2_android_school.manage.model.Mas
import com.google.gson.reflect.TypeToken

/**
 * 好友列表資料處理
 */
class ManageMasViewModel : ViewModel() {
    // 原始課程列表
    private var maList = mutableListOf<ManageAccountBean>()
    // 受監控的LiveData，一旦指派新值就會更新課程列表畫面
    val mas: MutableLiveData<List<ManageAccountBean>> by lazy { MutableLiveData<List<ManageAccountBean>>() }

    init {
        loadMas()
    }

    /**
     * 如果搜尋條件為空字串，就顯示原始課程列表；否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            mas.value = maList
        } else {
            val searchMaList = mutableListOf<ManageAccountBean>()
            maList.forEach { ma ->
                if (ma.manageId?.contains(newText, true) == true) {
                    searchMaList.add(ma)
                }
            }
            mas.value = searchMaList
        }
    }
//    private fun loadMas() {
//
//        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/course/"
//        val type = object : TypeToken<List<CourseReportBean>>() {}.type
//        val list = requestTask<List<CourseReportBean>>(url, respBodyType = type)
//        for (classes in list!!) {
//            maList.add(ManageAccountBean())
//        }
//        this.maList = maList
//        this.mas.value = this.maList
    /** 模擬取得遠端資料 */
    private fun loadMas() {
        val maList = mutableListOf<ManageAccountBean>()
        maList.add(ManageAccountBean("123","","",""))
        maList.add(ManageAccountBean("1233","","",""))
        maList.add(ManageAccountBean("345","","",""))

        this.maList = maList
        this.mas.value = this.maList
    }
}