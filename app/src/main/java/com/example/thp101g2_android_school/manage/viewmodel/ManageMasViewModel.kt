package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.thp101g2_android_school.manage.model.Mas

/**
 * 好友列表資料處理
 */
class ManageMasViewModel : ViewModel() {
    // 原始課程列表
    private var maList = mutableListOf<Mas>()
    // 受監控的LiveData，一旦指派新值就會更新課程列表畫面
    val mas: MutableLiveData<List<Mas>> by lazy { MutableLiveData<List<Mas>>() }

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
            val searchMaList = mutableListOf<Mas>()
            maList.forEach { ma ->
                if (ma.manageID.contains(newText, true)) {
                    searchMaList.add(ma)
                }
            }
            mas.value = searchMaList
        }
    }

    /** 模擬取得遠端資料 */
    private fun loadMas() {
        val maList = mutableListOf<Mas>()
        maList.add(Mas("123",))
        maList.add(Mas("1233",))
        maList.add(Mas("345",))

        this.maList = maList
        this.mas.value = this.maList
    }
}