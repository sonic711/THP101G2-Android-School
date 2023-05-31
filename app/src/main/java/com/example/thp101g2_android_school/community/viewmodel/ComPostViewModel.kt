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
import com.google.gson.JsonObject
import java.sql.Timestamp
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

    fun addPost(): Int? {

        if (secClassId.value.isNullOrEmpty()) return 0

        if (title.value.isNullOrEmpty()) return 0

        if (content.value.isNullOrEmpty()) return 0
        val postBean = ForPostBean()
        postBean.memberNo = memberId.value
        postBean.comSecClassId = secClassId.value
        postBean.comPostTitle = title.value?.trim()
        postBean.comPostContent = content.value?.trim()
        postBean.comPostStatus = private.value
        postBean.labels = labels.value

        val respbody = requestTask<JsonObject>("$url/community/post", "POST", postBean)
        respbody?.get("msg")?.asInt.let {
            return it
        }

    }


    private fun loadData() {
        val member = Member("1", "Vivi", R.drawable.com_mary)

        this.memberId.value = member.id
        this.memberName.value = member.name
        this.memberImg.value = member.img
//
//
//        val now = ZonedDateTime.now()
//        val formatterFULL = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
//        this.postTime.value = now.format(formatterFULL)
    }

    inner class Member(var id: String, var name: String, var img: Int)
}