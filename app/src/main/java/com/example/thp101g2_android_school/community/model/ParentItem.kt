package com.example.thp101g2_android_school.community.model

import java.io.Serializable

/**
 * 社群主類別
 * */
data class ParentItem(var parentName: String, var parentImg: Int, var childs: List<ChildItem>): Serializable {
}