package com.example.thp101g2_android_school.point.model

import java.io.Serializable
import java.sql.Timestamp

data class Point(
    val pointChangedId:String,
    val memberNo:String,
    val commentId:String,
    val orderId:String,
    val studentCourseId:String,
    val loginRecordId:String,
    val valueOfChanged:Int,
    val creatAt:String,
    val rewardPoints:String)