package com.example.thp101g2_android_school.community.model

import java.io.Serializable

/*
*  需要join 會員跟次分類
* */

data class Post(
    var post: PostBean,
    var labels: List<Label>,

) : Serializable