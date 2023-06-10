package com.example.thp101g2_android_school.community.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thp101g2_android_school.app.requestTask
import com.example.thp101g2_android_school.app.url
import com.example.thp101g2_android_school.community.model.Label
import com.example.thp101g2_android_school.community.model.Post
import com.example.thp101g2_android_school.community.model.PostBean
import com.example.thp101g2_android_school.member.model.Member
import com.google.gson.reflect.TypeToken

class ComFollowViewModel : ViewModel() {
    val followedPosts: MutableLiveData<List<Post>> by lazy { MutableLiveData<List<Post>>() }
    var followedPostList = mutableListOf<Post>()

    fun loadData(member: Member): Boolean {
        val type = object : TypeToken<List<PostBean>>() {}.type
        val list = requestTask<List<PostBean>>("$url/community/post", "OPTIONS", member, type) ?: return false
        val newList = mutableListOf<Post>()
        for (i in 0 until list.size - 1) {
            val comPostId = list[i].comPostId
            // 第一筆資料
            if (i == 0) {
                // 建立第一篇文章
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
                newList.add(
                    Post(
                        list[i].comPostId,
                        list[i].memberNo,
                        list[i].userId,
                        list[i].nickName,
                        list[i].profilePhoto,
                        list[i].comSecClassId,
                        list[i].comSecClassName,
                        list[i].comPostTitle,
                        list[i].comPostContent,
                        comPostTime = list[i].comPostTime,
                        comPostStatus = list[i].comPostStatus,
                        comPostAccessSetting = list[i].comPostAccessSetting,
                        labels = labels
                    )
                )
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
                    newList.add(
                        Post(
                            list[i].comPostId,
                            list[i].memberNo,
                            list[i].userId,
                            list[i].nickName,
                            list[i].profilePhoto,
                            list[i].comSecClassId,
                            list[i].comSecClassName,
                            list[i].comPostTitle,
                            list[i].comPostContent,
                            comPostTime = list[i].comPostTime,
                            comPostStatus = list[i].comPostStatus,
                            comPostAccessSetting = list[i].comPostAccessSetting,
                            labels = newchildItems
                        )
                    )
                    // 如果兩筆資料分類一致，就把該資料放入子分類集合中
                }
            }
        }
        followedPostList = newList
        followedPosts.postValue(followedPostList)
        return true
    }
}