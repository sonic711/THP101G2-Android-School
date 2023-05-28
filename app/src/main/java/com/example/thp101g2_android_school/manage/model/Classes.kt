package com.example.thp101g2_android_school.manage.model

import java.io.Serializable

class Classes(var imageId: Int, var classId: String, var name: String, var phone: String , var memberNo: String): Serializable
class Comms(var articleId :Int, var txtId :String): Serializable

class Members(var memberID :String, var Username: String, var phoneN: String):  Serializable

class Firms(var firmID :Int, var Firmname: String, var Firmphone: String):  Serializable

class Mas(var manageID :String):  Serializable

class Reports(var reportID :String):  Serializable

