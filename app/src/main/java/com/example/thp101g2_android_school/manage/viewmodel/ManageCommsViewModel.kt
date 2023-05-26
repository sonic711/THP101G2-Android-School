package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.manage.model.Comms

/**
 * 好友列表資料處理
 */
class ManageCommsViewModel : ViewModel() {
    // 原始課程列表
    private var commList = mutableListOf<Comms>()
    // 受監控的LiveData，一旦指派新值就會更新課程列表畫面
    val comms: MutableLiveData<List<Comms>> by lazy { MutableLiveData<List<Comms>>() }

    init {
        loadComms()
    }

    /**
     * 如果搜尋條件為空字串，就顯示原始課程列表；否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            comms.value = commList
        } else {
            val searchCommList = mutableListOf<Comms>()
            commList.forEach { comm ->
                if (comm.txtId.contains(newText, true)) {
                    searchCommList.add(comm)
                }
            }
            comms.value = searchCommList
        }
    }

    /** 模擬取得遠端資料 */
    private fun loadComms() {
        val commList = mutableListOf<Comms>()
        commList.add(Comms((R.drawable.mary), "1"))
        commList.add(Comms((R.drawable.mary), "2"))
        commList.add(Comms((R.drawable.mary), "3"))

        this.commList = commList
        this.comms.value = this.commList
    }
}