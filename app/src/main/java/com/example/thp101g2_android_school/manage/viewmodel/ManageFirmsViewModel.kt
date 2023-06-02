package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R

import com.example.thp101g2_android_school.manage.model.Firms

/**
 * 好友列表資料處理
 */
class  ManageFirmsViewModel : ViewModel() {
    // 原始課程列表
    private var firmList = mutableListOf<Firms>()
    // 受監控的LiveData，一旦指派新值就會更新課程列表畫面
    val firms: MutableLiveData<List<Firms>> by lazy { MutableLiveData<List<Firms>>() }

    init {
        loadFirms()
    }

    /**
     * 如果搜尋條件為空字串，就顯示原始課程列表；否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            firms.value = firmList
        } else {
            val searchFirmList = mutableListOf<Firms>()
            firmList.forEach { firm ->
                if (firm.Firmname.contains(newText, true)) {
                    searchFirmList.add(firm)
                }
            }
            firms.value = searchFirmList
        }
    }

    /** 模擬取得遠端資料 */
    private fun loadFirms() {
        val firmList = mutableListOf<Firms>()
        firmList.add(Firms(R.drawable.mary, "1","2020202"))
        firmList.add(Firms(R.drawable.mary, "2","9993333"))
        firmList.add(Firms(R.drawable.mary,"3","13155522"))

        this.firmList = firmList
        this.firms.value = this.firmList
    }
}