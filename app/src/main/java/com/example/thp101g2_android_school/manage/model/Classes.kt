package com.example.thp101g2_android_school.manage.model

import androidx.fragment.app.Fragment
import java.io.Serializable

class Classes(var imageId: Int,
              var classId: String,
              var classname: String,
              var phone: String ,
              var memberNo: String): Serializable
//課程

class Comms(var imageId: Int,
            var articleId :String,
            var txtId :String): Serializable
//社群

class Members(var imageId: Int,
              var memberID :String,
              var Username: String,
              var phoneN: String,
              var state: Boolean):  Serializable
//會員

class Firms(var firmID :Int,
            var Firmname: String,
            var Firmphone: String):  Serializable
//廠商

class Mas(var manageID :String):  Serializable
//管理員設置


class Reports(var reportID :String  ):  Serializable
//檢舉管理






