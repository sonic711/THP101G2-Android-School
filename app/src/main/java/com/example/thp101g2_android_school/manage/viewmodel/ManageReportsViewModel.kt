package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.manage.model.Reports

/**
 * 好友列表資料處理
 */
class ManageReportsViewModel : ViewModel() {
    // 原始課程列表
    private var reportList = mutableListOf<Reports>()
    // 受監控的LiveData，一旦指派新值就會更新課程列表畫面
    val reports: MutableLiveData<List<Reports>> by lazy { MutableLiveData<List<Reports>>() }

    init {
        loadReports()
    }

    /**
     * 如果搜尋條件為空字串，就顯示原始課程列表；否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            reports.value = reportList
        } else {
            val searchReportList = mutableListOf<Reports>()
            reportList.forEach { report ->
                if (report.reportID.contains(newText, true)) {
                    searchReportList.add(report)
                }
            }
            reports.value = searchReportList
        }
    }

    /** 模擬取得遠端資料 */
    private fun loadReports() {
        val reportList = mutableListOf<Reports>()
        reportList.add(Reports("123",))
        reportList.add(Reports("1233",))
        reportList.add(Reports("345",))

        this.reportList = reportList
        this.reports.value = this.reportList
    }
}