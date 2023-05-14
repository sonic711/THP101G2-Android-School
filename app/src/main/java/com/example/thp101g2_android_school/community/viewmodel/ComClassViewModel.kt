package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.community.model.ChildItem
import com.example.thp101g2_android_school.community.model.ParentItem

class ComClassViewModel : ViewModel() {
    private var parentList = mutableListOf<ParentItem>()
    val parents: MutableLiveData<List<ParentItem>> by lazy { MutableLiveData<List<ParentItem>>() }
    init {
        loadData()
    }

    private fun loadData() {

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