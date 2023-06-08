package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.manage.model.CourseReportBean
import com.google.gson.reflect.TypeToken

//import com.example.thp101g2_android_school.manage.model.Classes

/**
 * 好友列表資料處理
 */
class ManageClassesViewModel : ViewModel() {

//    fun updateCourseReport(courseReportBean: CourseReportBean) {
//        val i = 5
//    }
    // 原始課程列表
    private var classList = mutableListOf<CourseReportBean>()

    // 受監控的LiveData，一旦指派新值就會更新課程列表畫面
    val classes: MutableLiveData<List<CourseReportBean>> by lazy { MutableLiveData<List<CourseReportBean>>() }





    /**
     * 如果搜尋條件為空字串，就顯示原始課程列表；否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            classes.value = classList
        } else {
            val searchClassList = mutableListOf<CourseReportBean>()
            classList.forEach { classes ->
                if (classes.courseId.toString() == newText) {
                    searchClassList.add(classes)
                }//上面可能有點問題 ==?
            }
            classes.value = searchClassList
        }
    }
    init {
        loadClasses()
    }

    private fun loadClasses() {

        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/coursereport/"
        val type = object : TypeToken<List<CourseReportBean>>() {}.type
        val list = requestTask<List<CourseReportBean>>(url, respBodyType = type)
        for (classes in list!!) {
            classList.add(classes)
        }
        this.classList = classList
        this.classes.value = this.classList

    /** 模擬取得遠端資料 */
//    private fun loadClasses() {
//        val classList = mutableListOf<CourseReportBean>()
//        classList.add(CourseReportBean( 1, 1,1,1,"",))
//        classList.add(CourseReportBean(2, 2,2,2,""))
//        classList.add(CourseReportBean(3, 3,3,3,"11",))
//
//        this.classList = classList
//        this.classes.value = this.classList
    }
}