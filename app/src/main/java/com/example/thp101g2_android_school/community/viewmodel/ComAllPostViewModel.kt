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
        childItems1.add(Label("Chinese"))
        childItems1.add(Label("Easy"))
        childItems1.add(Label("Damnnn"))
        postList.add(Post("1","Sean", R.drawable.com_julia, "Java", "1/2","Java Basic","Testing",childItems1))

        val childItems2 = ArrayList<Label>()
        childItems2.add(Label("English"))
        childItems2.add(Label("文章標籤"))
        childItems2.add(Label("履歷"))
        postList.add(Post("2","Mary",R.drawable.com_mary, "Resume", "12/25","piano","Testing", childItems2))

        val childItems3 = ArrayList<Label>()
        childItems3.add(Label("English"))
        childItems3.add(Label("文章標籤"))
        postList.add(Post("3","John",R.drawable.com_sue, "Language", "2/22","This is how you eat big-mac","Testing", childItems3))


        this.postList = postList
        this.posts.value = this.postList
    }
}