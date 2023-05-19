package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.community.model.ChildItem
import com.example.thp101g2_android_school.community.model.Classes
import com.example.thp101g2_android_school.community.model.ParentItem
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

class ComClassViewModel : ViewModel() {
    private var parentList = mutableListOf<ParentItem>()
    val parents: MutableLiveData<List<ParentItem>> by lazy { MutableLiveData<List<ParentItem>>() }

    init {
        loadData()
    }

    private fun loadData() {
        // TODO 可以抓到資料 但還沒處理好
        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/community/class"
        val type = object : TypeToken<List<Classes>>() {}.type
        val list = requestTask<List<Classes>>(url, respBodyType = type)
        val childItems = mutableListOf<ChildItem>()


        for (i in 0 until list!!.size - 1) {
            val comMainClassId = list[i].comMainClassId
            val comMainClassName = list[i].comMainClassName
            val comSecClassId = list[i].comSecClassId
            val comSecClassName = list[i].comSecClassName
            println(parentList)
            // 第一筆資料，裝進子分類集合中，並創立一個主分類集合
            if (i == 0) {
                childItems.add(ChildItem(comSecClassName, R.drawable.com_c))
                parentList.add(ParentItem(comMainClassName, R.drawable.com_console, childItems))
                continue
            }
            // 第二筆資料開始，如果此筆跟上一筆的主分類不一樣
            // 就新增一個主分類集合，並把子分類裝進該集合中
            if (comMainClassId != list[i - 1].comMainClassId) {

                val newchildItems = mutableListOf<ChildItem>()
                parentList.add(ParentItem(comMainClassName, R.drawable.com_console, newchildItems))
                newchildItems.add(ChildItem(comSecClassName, R.drawable.com_c))
                // 如果兩筆資料分類一致，就把該資料放入子分類集合中
            } else {
                // TODO 這邊有問題 ...
                childItems.add(ChildItem(comSecClassName, R.drawable.com_c))
            }
        }
        this.parents.value = parentList

        // 假資料
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