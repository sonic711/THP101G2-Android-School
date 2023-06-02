package com.example.thp101g2_android_school.manage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.manage.model.Classes

/**
 * 好友列表資料處理
 */
class ManageClassesViewModel : ViewModel() {
    // 原始課程列表
    private var classList = mutableListOf<Classes>()
    // 受監控的LiveData，一旦指派新值就會更新課程列表畫面
    val classes: MutableLiveData<List<Classes>> by lazy { MutableLiveData<List<Classes>>() }

    init {
        loadClasses()
    }

    /**
     * 如果搜尋條件為空字串，就顯示原始課程列表；否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            classes.value = classList
        } else {
            val searchClassList = mutableListOf<Classes>()
            classList.forEach { classes ->
                if (classes.classId.contains(newText, true)) {
                    searchClassList.add(classes)
                }
            }
            classes.value = searchClassList
        }
    }

    /** 模擬取得遠端資料 */
    private fun loadClasses() {
        val classList = mutableListOf<Classes>()
        classList.add(Classes(R.drawable.ivy, "1", "Java?Js?","0933333333","11"))
        classList.add(Classes(R.drawable.mary, "2", "Andiord?","0912345678","11"))
        classList.add(Classes(R.drawable.ivy, "3", "Kotlin?","0922222222","11"))
        classList.add(Classes(R.drawable.ivy, "1", "Java?Js?","0933333333","11"))
        classList.add(Classes(R.drawable.mary, "2", "Andiord?","0912345678","11"))
        classList.add(Classes(R.drawable.ivy, "3", "Kotlin?","0922222222","11"))
        classList.add(Classes(R.drawable.ivy, "1", "Java?Js?","0933333333","11"))
        classList.add(Classes(R.drawable.mary, "2", "Andiord?","0912345678","11"))
        classList.add(Classes(R.drawable.ivy, "3", "Kotlin?","0922222222","11"))
        classList.add(Classes(R.drawable.ivy, "1", "Java?Js?","0933333333","11"))
        classList.add(Classes(R.drawable.mary, "2", "Andiord?","0912345678","11"))
        classList.add(Classes(R.drawable.ivy, "3", "Kotlin?","0922222222","11"))

        this.classList = classList
        this.classes.value = this.classList
    }
}