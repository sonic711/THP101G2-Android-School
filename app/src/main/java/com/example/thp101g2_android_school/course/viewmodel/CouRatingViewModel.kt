package com.example.thp101g2_android_school.course.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.course.model.Comment
import com.google.gson.reflect.TypeToken

class CouRatingViewModel : ViewModel() {
    private var ratingList = mutableListOf<Comment>()
    val ratings: MutableLiveData<List<Comment>> by lazy { MutableLiveData<List<Comment>>() }

init {
    loadData()
}
    private fun loadData(){
        val url = "$url/comment/"
        val type = object  : TypeToken<List<Comment>>() {}.type
        val list = requestTask<List<Comment>>(url, respBodyType = type)
        for (ratings in list!!){
            ratingList.add(ratings)
        }
        this.ratingList = ratingList
        this.ratings.value = this.ratingList
    }
}