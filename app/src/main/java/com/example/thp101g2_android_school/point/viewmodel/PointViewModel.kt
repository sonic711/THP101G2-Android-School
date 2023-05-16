package com.example.thp101g2_android_school.point.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.point.model.Point

class PointViewModel : ViewModel() {
    private var reasonList = mutableListOf<Point>()
    val reasons: MutableLiveData<List<Point>> by lazy { MutableLiveData<List<Point>>() }

    init {
        loadReasons()
    }


    private fun loadReasons() {
        val point1 = "積分+10"
        val point2 = "積分-5"
        val point3 = "積分+5"
        val point4 = "積分-20"
        val reason1 = "商務日語\t課程完成率達50%"
        val reason2 = "使用積分折抵"
        val reason3 = "如何培養職場即戰力\t評分完成"
        val reason4 = "使用積分折抵"
        val dateString1 = "2019/01/02\t17:50:23"
        val dateString2 = "2018/05/02\t04:50:35"
        val dateString3 = "2017/02/02\t11:42:14"
        val dateString4 = "2016/10/02\t17:23:52"
        val reasonList = mutableListOf<Point>()
        reasonList.add(Point(point1,reason1,dateString1))
        reasonList.add(Point(point2,reason2,dateString2))
        reasonList.add(Point(point3,reason3,dateString3))
        reasonList.add(Point(point4,reason4,dateString4))
        this.reasonList = reasonList
        this.reasons.value = reasonList


    }
}