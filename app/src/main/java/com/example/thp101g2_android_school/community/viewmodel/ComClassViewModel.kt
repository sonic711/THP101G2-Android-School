package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thp101g2_android_school.app.getStringResourceId
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.ChildItem
import com.example.thp101g2_android_school.community.model.ClassBean
import com.example.thp101g2_android_school.community.model.ParentItem
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class ComClassViewModel : ViewModel() {
    private var parentList = mutableListOf<ParentItem>()
    val parents: MutableLiveData<List<ParentItem>> by lazy { MutableLiveData<List<ParentItem>>() }

    init {
        viewModelScope.launch { loadData() }
    }

    private fun loadData() {

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

//        // 假資料
//        val childItems1 = ArrayList<ChildItem>()
//        childItems1.add(ChildItem("C", R.drawable.com_c))
//        childItems1.add(ChildItem("C++", R.drawable.com_cplusplus))
//        childItems1.add(ChildItem("Java", R.drawable.com_java))
//        childItems1.add(ChildItem("C#", R.drawable.com_csharp))
//
//        parentList.add(ParentItem("Game Development", R.drawable.com_console, childItems1))
//
//
//        val childItem2 = ArrayList<ChildItem>()
//        childItem2.add(ChildItem("Kotlin", R.drawable.com_kotlin))
//        childItem2.add(ChildItem("XML", R.drawable.com_xml))
//        childItem2.add(ChildItem("Java", R.drawable.com_java))
//        parentList.add(
//            ParentItem(
//                "Android Development",
//                R.drawable.com_android,
//                childItem2
//            )
//        )
//        val childItem3 = ArrayList<ChildItem>()
//        childItem3.add(ChildItem("JavaScript", R.drawable.com_javascript))
//        childItem3.add(ChildItem("HTML", R.drawable.com_html))
//        childItem3.add(ChildItem("CSS", R.drawable.com_css))
//        parentList.add(
//            ParentItem(
//                "Front End Web",
//                R.drawable.com_front_end,
//                childItem3
//            )
//        )
//        val childItem4 = ArrayList<ChildItem>()
//        childItem4.add(ChildItem("Julia", R.drawable.com_julia))
//        childItem4.add(ChildItem("Python", R.drawable.com_python))
//        childItem4.add(ChildItem("R", R.drawable.com_r))
//        parentList.add(
//            ParentItem(
//                "Artificial Intelligence",
//                R.drawable.com_ai,
//                childItem4
//            )
//        )
//        val childItem5 = ArrayList<ChildItem>()
//        childItem5.add(ChildItem("Java", R.drawable.com_java))
//        childItem5.add(ChildItem("Python", R.drawable.com_python))
//        childItem5.add(ChildItem("PHP", R.drawable.com_c))
//        childItem5.add(ChildItem("JavaScript", R.drawable.com_javascript))
//        parentList.add(
//            ParentItem(
//                "Back End Web",
//                R.drawable.com_backend,
//                childItem5
//            )
//        )
//        this.parents.value = parentList
    }
}