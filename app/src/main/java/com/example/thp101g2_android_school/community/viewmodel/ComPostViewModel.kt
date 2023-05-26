package com.example.thp101g2_android_school.community.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.ForPostBean
import com.example.thp101g2_android_school.community.model.Label
import com.example.thp101g2_android_school.community.model.Post
import com.example.thp101g2_android_school.community.model.PostBean
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@RequiresApi(Build.VERSION_CODES.O)
class ComPostViewModel : ViewModel() {

    val memberId: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val memberName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val memberImg: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val postTime: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val secClassId: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val secClassName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val private: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(true) }
    val title: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val content: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val labels: MutableLiveData<List<Label>> = MutableLiveData<List<Label>>()

    init {
        loadData()
    }

    fun addPost() {

        val postBean = ForPostBean()
        if (secClassId.value.isNullOrEmpty()) return

        if (title.value.isNullOrEmpty()) return

        if (content.value.isNullOrEmpty()) return
        postBean.memberNo = memberId.value!!
        postBean.comSecClassId = secClassId.value!!
        postBean.comPostTitle = title.value!!
        postBean.comPostContent = content.value!!
        postBean.comPostStatus = private.value!!
        postBean.labels = labels.value
//        println(postBean)
        val respbody = requestTask<JsonObject>("$url/community/post", "POST", postBean)

    }

    private fun loadData() {
        val member = Member("2", "Vivi", R.drawable.com_mary)

        this.memberId.value = member.id
        this.memberName.value = member.name
        this.memberImg.value = member.img


        val now = ZonedDateTime.now()
        val formatterFULL = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
        this.postTime.value = now.format(formatterFULL)
    }

    inner class Member(var id: String, var name: String, var img: Int)
}