package com.example.thp101g2_android_school.community.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.ActivityViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.community.model.Label
import com.example.thp101g2_android_school.community.model.Post
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@RequiresApi(Build.VERSION_CODES.O)
class ComPostViewModel : ViewModel() {
    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>() }
    val post: MutableLiveData<Post> by lazy { MutableLiveData<Post>() }

    init {
        loadData()
    }
    private fun loadData(){
        val member = Member("Sean", R.drawable.com_mary)
        this.member.value = member

        val time = LocalDateTime.now()
        val now = ZonedDateTime.now()
        val formatterFULL = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
        this.post.value?.post?.comPostTime = now.format(formatterFULL)
    }
    inner class Member(var name: String, var img : Int)
}