package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.R
import com.example.thp101g2_android_school.community.model.ChildItem
import com.example.thp101g2_android_school.community.model.Label
import com.example.thp101g2_android_school.community.model.ParentItem
import com.example.thp101g2_android_school.community.model.Post

class ComAllPostViewModel : ViewModel() {

    // 原始Post列表
    private var postList = mutableListOf<Post>()
    // 受監控的LiveData，一旦指派新值就會更新畫面
    val posts: MutableLiveData<List<Post>> by lazy { MutableLiveData<List<Post>>() }

    init {
        loadPosts()
    }
    private fun loadPosts(){
        val childItems1 = ArrayList<Label>()
        childItems1.add(Label("1", "1","中文", "2"))
        childItems1.add(Label("1", "1","簡單", "2"))
        childItems1.add(Label("1", "1","免費", "2"))

        postList.add(Post(
            "1",
            "1",
            "Sean",
            R.drawable.com_julia,
            "secClassId1",
            "Java板",
            "這是一篇Java教學",
            "Post Content 1",
            childItems1,
            "2023-05-21 12:00:00",
            true,
            true
        ))

        val childItems2 = ArrayList<Label>()
        childItems2.add(Label("1", "1","英文", "2"))
        childItems2.add(Label("1", "1","高難度", "2"))
        childItems2.add(Label("1", "1","商用", "2"))

        postList.add(Post(
            "2",
            "2",
            "Mary",
            R.drawable.com_mary,
            "secClassId1",
            "英文板",
            "這是一篇英文教學",
            "Post Content 2",
            childItems2,
            "2023-05-21 12:00:00",
            true,
            true
        ))

        val childItems3 = ArrayList<Label>()
        childItems3.add(Label("1", "1","爆料", "2"))
        childItems3.add(Label("1", "1","推爆", "2"))
        postList.add(Post(
            "3",
            "3",
            "老闆",
            R.drawable.com_android,
            "secClassId1",
            "八卦板",
            "這一一篇爆料文章！",
            "爆料爆料爆料爆料爆料爆料",
            childItems3,
            "2023-05-21 12:00:00",
            true,
            true
        ))



        this.postList = postList
        this.posts.value = this.postList
    }
}