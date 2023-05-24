package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.Label
import com.example.thp101g2_android_school.community.model.Post
import com.example.thp101g2_android_school.community.model.PostBean
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class ComAllPostViewModel : ViewModel() {

    // 原始Post列表
    private var postList = mutableListOf<Post>()

    // 受監控的LiveData，一旦指派新值就會更新畫面
    val posts: MutableLiveData<List<Post>> by lazy { MutableLiveData<List<Post>>() }

    init {
        viewModelScope.launch { loadPosts() }
    }

    private fun loadPosts() {

        val url = "$url/community/post"
        val type = object : TypeToken<List<PostBean>>() {}.type
        val list = requestTask<List<PostBean>>(url, respBodyType = type) ?: return

        for (i in 0 until list.size - 1) {
            val comPostId = list[i].comPostId
            // 第一筆資料
            if (i == 0) {
                // 建立第一個主分類
                val labels = mutableListOf<Label>()
                // 遍歷資料庫所有資料
                for (item in list) {
                    // 如果該次分類的主分類id與第一個主分類id相同的話
                    if (item.comPostId == comPostId) {
                        // 把該次分類放入該主分類的屬性中
                        labels.add(
                            Label(
                                item.comPostLabelId,
                                item.comPostId,
                                item.comPostLabelName,
                                item.comPostLabelTime
                            )
                        )
                    }
                }
                postList.add(Post(list[i], labels))
                continue
            } else {
                // 如果這一個主分類id 不等於 上一個主分類 id
                if (comPostId != list[i - 1].comPostId) {
                    // 建立新的子類別集合
                    val newchildItems = mutableListOf<Label>()
                    for (item in list) {
                        // 如果主類別id一樣
                        if (item.comPostId == comPostId) {
                            // 就把該分類放進集合
                            newchildItems.add(
                                Label(
                                    item.comPostLabelId,
                                    item.comPostId,
                                    item.comPostLabelName,
                                    item.comPostLabelTime
                                )
                            )
                        }
                    }
                    postList.add(Post(list[i], newchildItems))
                    // 如果兩筆資料分類一致，就把該資料放入子分類集合中
                }
            }
        }
        this.postList = postList
        this.posts.value = this.postList
    }
}