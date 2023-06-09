package com.example.thp101g2_android_school.course.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.course.model.Comment
import com.example.thp101g2_android_school.course.model.FavCourse
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.reflect.TypeToken

class CouRateViewModel : ViewModel() {
   val rates: MutableLiveData<Comment> by lazy { MutableLiveData<Comment>() }
   val point: MutableLiveData<String> by lazy { MutableLiveData<String>() }
   val comment: MutableLiveData<String> by lazy { MutableLiveData<String>() }
   init {
      val member: Member? = requestTask("http://10.0.2.2:8080/THP101G2-WebServer-School/members", "OPTIONS")
      member?.memberNo
   }

//   private var rateList = mutableListOf<Comment>()
//
//   init {
//      loadData()
//   }
//
//   fun loadData() {
//      val url = "$url/comment/"
//      val type = object : TypeToken<List<Comment>>() {}.type
//      val list = requestTask<List<Comment>>(url, respBodyType = type)
//      for (rates in list!!) {
//         rateList.add(rates)
//      }
//      this.rateList = rateList
//      this.rates.value = this.rateList
//   }

}