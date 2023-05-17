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
//        val url = "http://10.0.2.2:8080/THP101G2-WebServer-School/community/class"
//        val type = object : TypeToken<List<Classes>>(){}.type
//        val list = requestTask<List<Classes>>(url, respBodyType = type)
//        val childItems = ArrayList<ChildItem>()
//
//        for(classes in list!!){
//            val comMainClassId = classes.comMainClassId
//            val comMainClassName = classes.comMainClassName
//            val comSecClassId = classes.comSecClassId
//            val comSecClassName = classes.comSecClassName
//            // 如果主分類編號 = 上一個的主分類編號 就新增到childItems
//            if(comMainClassId == (list.get(comSecClassId.toInt() - 1)).comMainClassId) {
//                childItems.add(ChildItem(comSecClassName, R.drawable.com_c))
//                continue
//            // 如果不等於，就新增一個List，並新增到該List之中
//            }else{
//                val childItems = ArrayList<ChildItem>()
//                childItems.add(ChildItem(comSecClassName, R.drawable.com_c))
//
//            }
//
//            parentList.add(ParentItem(comMainClassName, R.drawable.com_console, childItems))
//        }
//
//        this.parents.value = parentList






        // 假資料
        val childItems1 = ArrayList<ChildItem>()
        childItems1.add(ChildItem("C", R.drawable.com_c))
        childItems1.add(ChildItem("C++", R.drawable.com_cplusplus))
        childItems1.add(ChildItem("Java", R.drawable.com_java))
        childItems1.add(ChildItem("C#", R.drawable.com_csharp))

        parentList.add(ParentItem("Game Development", R.drawable.com_console, childItems1))


        val childItem2 = ArrayList<ChildItem>()
        childItem2.add(ChildItem("Kotlin", R.drawable.com_kotlin))
        childItem2.add(ChildItem("XML", R.drawable.com_xml))
        childItem2.add(ChildItem("Java", R.drawable.com_java))
        parentList.add(
            ParentItem(
                "Android Development",
                R.drawable.com_android,
                childItem2
            )
        )
        val childItem3 = ArrayList<ChildItem>()
        childItem3.add(ChildItem("JavaScript", R.drawable.com_javascript))
        childItem3.add(ChildItem("HTML", R.drawable.com_html))
        childItem3.add(ChildItem("CSS", R.drawable.com_css))
        parentList.add(
            ParentItem(
                "Front End Web",
                R.drawable.com_front_end,
                childItem3
            )
        )
        val childItem4 = ArrayList<ChildItem>()
        childItem4.add(ChildItem("Julia", R.drawable.com_julia))
        childItem4.add(ChildItem("Python", R.drawable.com_python))
        childItem4.add(ChildItem("R", R.drawable.com_r))
        parentList.add(
            ParentItem(
                "Artificial Intelligence",
                R.drawable.com_ai,
                childItem4
            )
        )
        val childItem5 = ArrayList<ChildItem>()
        childItem5.add(ChildItem("Java", R.drawable.com_java))
        childItem5.add(ChildItem("Python", R.drawable.com_python))
        childItem5.add(ChildItem("PHP", R.drawable.com_c))
        childItem5.add(ChildItem("JavaScript", R.drawable.com_javascript))
        parentList.add(
            ParentItem(
                "Back End Web",
                R.drawable.com_backend,
                childItem5
            )
        )
        this.parents.value = parentList
    }
}