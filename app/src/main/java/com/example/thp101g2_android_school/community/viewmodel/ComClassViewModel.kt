package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thp101g2_android_school.app.getStringResourceId
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.ChildItem
import com.example.thp101g2_android_school.community.model.ClassBean
import com.example.thp101g2_android_school.community.model.FollowClassBean
import com.example.thp101g2_android_school.community.model.ParentItem
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class ComClassViewModel : ViewModel() {

    private var parentList = mutableListOf<ParentItem>()
    val parents: MutableLiveData<List<ParentItem>> by lazy { MutableLiveData<List<ParentItem>>() }
    var followList = listOf<FollowClassBean>()

    init {
        viewModelScope.launch {
            loadClasses()
        }
    }

    private fun loadClasses() {

        val url = "$url/community/class"
        val type = object : TypeToken<List<ClassBean>>() {}.type
        val list = requestTask<List<ClassBean>>(url, respBodyType = type) ?: return

        for (i in 0 until list.size - 1) {
            val comMainClassId = list[i].comMainClassId
            val comMainClassName = list[i].comMainClassName
            // 第一筆資料
            if (i == 0) {
                // 建立第一個主分類
                val childItems = mutableListOf<ChildItem>()
                // 遍歷資料庫所有資料
                for (item in list) {
                    // 如果該次分類的主分類id與第一個主分類id相同的話
                    if (item.comMainClassId == comMainClassId) {
                        // 把該次分類放入該主分類的屬性中
                        childItems.add(
                            ChildItem(
                                item.comMainClassId,
                                item.comMainClassName,
                                item.comSecClassId,
                                item.comSecClassName,
                                getStringResourceId(item.comSecClassName)
                            )
                        )
                    }
                }
                parentList.add(ParentItem(comMainClassName, getStringResourceId(comMainClassName), childItems))
                continue
            } else {
                // 如果這一個主分類id 不等於 上一個主分類 id
                if (comMainClassId != list[i - 1].comMainClassId) {
                    // 建立新的子類別集合
                    val newchildItems = mutableListOf<ChildItem>()
                    for (item in list) {
                        // 如果主類別id一樣
                        if (item.comMainClassId == comMainClassId) {
                            // 就把該分類放進集合
                            newchildItems.add(
                                ChildItem(
                                    item.comMainClassId,
                                    item.comMainClassName,
                                    item.comSecClassId,
                                    item.comSecClassName,
                                    getStringResourceId(item.comSecClassName)
                                )
                            )
                        }
                    }
                    parentList.add(ParentItem(comMainClassName, getStringResourceId(comMainClassName), newchildItems))
                    // 如果兩筆資料分類一致，就把該資料放入子分類集合中
                }
            }
        }
        this.parents.value = parentList

    }

    // 取得目前登入會員編號的所有追蹤次分類
    fun getFollowClass(member: Member) {
        val memberId = member.memberNo
        val url = "$url/member/followClass/$memberId"
        val type = object : TypeToken<List<FollowClassBean>>() {}.type
        val followClass = requestTask<List<FollowClassBean>>(url, respBodyType = type) ?: return
        this.followList = followClass
    }
}