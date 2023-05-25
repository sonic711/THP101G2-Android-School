package com.example.thp101g2_android_school.community.model

import java.io.Serializable

/**
 * 社群次類別
 * */
data class ChildItem(var comMainClassId: String,var comMainClassName: String, val comSecClassId: String, var comSecClassName: String, var childImg: Int): Serializable {
}